package com.giantLink.RH.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Role;
import com.giantLink.RH.entities.User;
import com.giantLink.RH.exceptions.ResourceNotFoundException;
import com.giantLink.RH.mappers.UserMapper;
import com.giantLink.RH.models.request.LoginRequest;
import com.giantLink.RH.models.request.RegisterRequest;
import com.giantLink.RH.models.request.UpdateProfileRequest;
import com.giantLink.RH.models.response.LoginResponse;
import com.giantLink.RH.models.response.UserResponse;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.UserRepository;
import com.giantLink.RH.repositories.RoleRepository;
import com.giantLink.RH.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserResponse register(RegisterRequest request) {
        // Retrieve the employee associated with the given ID.
        Employee employee = employeeRepository.findById(request.getId_employee())
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), "id_employee", String.valueOf(request.getId_employee())));

        // Fetch roles from the request and find them in the role repository.
        List<Role> roles = request.getRoles().stream()
                .map(role -> roleRepository.findByRoleName(role.getRoleName())
                        .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), "id_role", String.valueOf(role.getId()))))
                .collect(Collectors.toList());

        // Create the user entity with the associated employee and roles, then save it to the database.
        var user = User.builder()
                .username(employee.getCin())
                .password(passwordEncoder.encode(request.getPassword()))
                .employee(employee)
                .roles(roles)
                .build();
        userRepository.save(user);
        return UserMapper.INSTANCE.entityToResponse(user);
    }

    @Override
    public List<LoginResponse> login(LoginRequest request) {
        // Authenticate the user with the provided credentials.
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        // Find the user based on the username.
        var user = userRepository.findByUsername(request.getUsername());
        List<LoginResponse> loginResponses = new ArrayList<>();
        for (Role role : user.getRoles()) {
            // Generate access and refresh tokens for each role associated with the user.
            var roleAccessToken = jwtService.generateTokenWithRole(role, user);
            var roleRefreshToken = jwtService.generateRefreshTokenWithRole(role, user);
            // Build the login response for each role and add it to the list.
            loginResponses.add(LoginResponse.builder().role(role.getRoleName()).accessToken(roleAccessToken).refreshToken(roleRefreshToken).build());
            // Log the role information.
            logger.info("Role: {} | Token: {} | refreshToken: {}", role.getRoleName(), roleAccessToken, roleRefreshToken);
        }
        return loginResponses;
    }


    public UserResponse addRoleToUser(Long id_user, Long id_role) {
        Optional<User> findUser = userRepository.findById(id_user);
        Optional<Role> findRole = roleRepository.findById(id_role);
        if (!findUser.isPresent()) {
            logger.error("User with id {} not found", id_user);
            throw new ResourceNotFoundException(User.class.getSimpleName(), "id_user", String.valueOf(id_user));
        }
        if (!findRole.isPresent()) {
            logger.error("Role with id {} not found", id_role);
            throw new ResourceNotFoundException(Role.class.getSimpleName(), "id_role", String.valueOf(id_role));
        }
        User user = findUser.get();
        user.getRoles().add(
                findRole.get()
        );
        return UserMapper.INSTANCE.entityToResponse(user);
    }

    public void deleteRoleFromUser(Long id_user, Long id_role) {
        Optional<User> findUser = userRepository.findById(id_user);
        Optional<Role> findRole = roleRepository.findById(id_role);
        if (!findUser.isPresent()) {
            logger.error("User with id {} not found", id_user);
            throw new ResourceNotFoundException(User.class.getSimpleName(), "id_user", String.valueOf(id_user));
        }
        if (!findRole.isPresent()) {
            logger.error("User with id {} not found", id_role);
            throw new ResourceNotFoundException(Role.class.getSimpleName(), "id_role", String.valueOf(id_role));
        }
        User appUser = findUser.get();
        appUser.getRoles().remove(findRole.get());
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        final String roleName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        roleName = jwtService.extractRoleFromToken(refreshToken);
        Optional<Role> findRole = roleRepository.findByRoleName(roleName);
        if (username != null && findRole.isPresent()) {
            Role role = findRole.get();
            var userDetails = userRepository.findByEmployeeCin(username).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                var accessToken = jwtService.generateRefreshTokenWithRole(role, userDetails);
                var authResponse = LoginResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }

    }


    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateUserProfile(Employee employee, UpdateProfileRequest updateRequest) {
        if (updateRequest.getFirstName() != null) {
            employee.setFirstName(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName() != null) {
            employee.setLastName(updateRequest.getLastName());
        }
        if (updateRequest.getEmail() != null) {
            employee.setEmail(updateRequest.getEmail());
        }
        if (updateRequest.getPhone() != null) {
            employee.setPhone(updateRequest.getPhone());
        }
        employeeRepository.save(employee);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}
