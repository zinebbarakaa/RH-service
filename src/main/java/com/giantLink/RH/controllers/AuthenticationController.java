package com.giantLink.RH.controllers;


import com.giantLink.RH.models.request.LoginRequest;
import com.giantLink.RH.models.request.RegisterRequest;
import com.giantLink.RH.models.response.LoginResponse;
import com.giantLink.RH.models.response.UserResponse;
import com.giantLink.RH.services.UserService;
import com.giantLink.RH.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@PreAuthorize("hasRole('SUPER_ADMIN')")
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<List<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }



    @PostMapping("/register")
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/add-role/{userId}/{roleId}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<UserResponse> addRoleToUser(@PathVariable Long userId,
                                                             @PathVariable Long roleId) {
        return new ResponseEntity<>(userServiceImpl.addRoleToUser(userId, roleId), HttpStatus.CREATED);
    }

    @PostMapping("/delete-role/{userId}/{roleId}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<String> deleteRoleFromUser(@PathVariable Long userId,
                                                     @PathVariable Long roleId) {
        userServiceImpl.deleteRoleFromUser(userId, roleId);
        return new ResponseEntity<>("the role is deleted!", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        userServiceImpl.refreshToken(request,response);
    }
}

