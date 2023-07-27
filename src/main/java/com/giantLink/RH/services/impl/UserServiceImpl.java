package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Role;
import com.giantLink.RH.entities.User;
import com.giantLink.RH.exceptions.RessourceNotFoundException;
import com.giantLink.RH.models.request.LoginRequest;
import com.giantLink.RH.models.request.RegisterRequest;
import com.giantLink.RH.models.response.LoginResponse;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.UserRepository;
import com.giantLink.RH.repositories.RoleRepository;
import com.giantLink.RH.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<LoginResponse> register(RegisterRequest request) {
        Optional<Employee> findEmployee = employeeRepository.findById(request.getId_employee());
        if (!findEmployee.isPresent()) {
            throw new RuntimeException("Employee Not found");
        }
        Employee employee = findEmployee.get();
        List<Role> roles = new ArrayList<>();
        for (Role role : request.getRoles()) {
            // Assuming AppRoleRepository has a method to find roles by roleName
            Optional<Role> findRole = roleRepository.findByRoleName(role.getRoleName());
            if (findRole.isPresent()) {
                roles.add(findRole.get());
            } else {
                throw new RessourceNotFoundException(Role.class.getSimpleName(),"id_role",String.valueOf(role.getId()));
            }
        }

        var user = User.builder()
                .username(employee.getCin())
                .password(passwordEncoder.encode(request.getPassword()))
                .employee(employeeRepository.findById(request.getId_employee()).get())
                .roles(roles)
                .build();

        userRepository.save(user);
//        var jwtToken = jwtService.generateToken(user);
        List<LoginResponse> loginResponses = new ArrayList<>();
        for (Role role : user.getRoles()) {
            var roleToken = jwtService.generateTokenWithRole(role, user);
            loginResponses.add(LoginResponse.builder().role(role.getRoleName()).token(roleToken).build());
            System.out.println("Role: " + role.getRoleName() + " | Token: " + roleToken);
        }

        return loginResponses;
    }

    @Override
    public List<LoginResponse> login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userRepository.findByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(user);
        for (Role role : user.getRoles()) {
            var roleToken = jwtService.generateTokenWithRole(role, user);
            System.out.println("Role: " + role.getRoleName() + " | Token: " + roleToken);
        }
        List<LoginResponse> loginResponses = new ArrayList<>();
        for (Role role : user.getRoles()) {
            var roleToken = jwtService.generateTokenWithRole(role, user);
            loginResponses.add(LoginResponse.builder().role(role.getRoleName()).token(roleToken).build());
            System.out.println("Role: " + role.getRoleName() + " | Token: " + roleToken);
        }
        return loginResponses;
    }

    public List<LoginResponse> addRoleToUser(Long id_user, Long id_role) {
        Optional<User> findUser = userRepository.findById(id_user);
        Optional<Role> findRole = roleRepository.findById(id_role);
        if (!findUser.isPresent()) {
            throw new RessourceNotFoundException(User.class.getSimpleName(),"id_user",String.valueOf(id_user));
        }
        if (!findRole.isPresent()) {
            throw new RessourceNotFoundException(Role.class.getSimpleName(),"id_role",String.valueOf(id_role));
        }
        User user = findUser.get();
        List<Role> roles = new ArrayList<>();
        roles.add(findRole.get());
        user.setRoles(roles);
        List<LoginResponse> loginResponses = new ArrayList<>();
        for (Role role : user.getRoles()) {
            var roleToken = jwtService.generateTokenWithRole(role, user);
            loginResponses.add(LoginResponse.builder().role(role.getRoleName()).token(roleToken).build());
            System.out.println("Role: " + role.getRoleName() + " | Token: " + roleToken);
        }
        return loginResponses;
    }

    public void deleteRoleFromUser(Long id_user, Long id_role) {
        Optional<User> findUser = userRepository.findById(id_user);
        Optional<Role> findRole = roleRepository.findById(id_role);
        if (!findUser.isPresent()) {
            throw new RessourceNotFoundException(User.class.getSimpleName(),"id_user",String.valueOf(id_user));
        }
        if (!findRole.isPresent()) {
            throw new RessourceNotFoundException(Role.class.getSimpleName(),"id_role",String.valueOf(id_role));
        }
        User appUser = findUser.get();
        List<Role> roles = appUser.getRoles();
        roles.remove(findRole.get());
        appUser.setRoles(roles);
        var jwtToken = jwtService.generateToken(appUser);
        for (Role role : appUser.getRoles()) {
            var roleToken = jwtService.generateTokenWithRole(role, appUser);
            System.out.println("Role: " + role.getRoleName() + " | Token: " + roleToken);
        }
    }
}
