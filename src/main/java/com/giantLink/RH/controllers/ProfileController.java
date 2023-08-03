package com.giantLink.RH.controllers;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.User;
import com.giantLink.RH.models.request.UpdateProfileRequest;
import com.giantLink.RH.models.response.PayrollResponse;
import com.giantLink.RH.services.PayrollService;
import com.giantLink.RH.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/employees/")
@RequiredArgsConstructor
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private PayrollService payrollService;

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('ADMIN_RH','EMPLOYEE','MANAGER_RH','DIRECTOR')")
    public ResponseEntity<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User authenticatedUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(authenticatedUser);
    }
    @PutMapping("/update-profile")
    @PreAuthorize("hasAnyRole('ADMIN_RH','EMPLOYEE','MANAGER_RH','DIRECTOR')")
    public ResponseEntity<String> updateProfile(@RequestBody UpdateProfileRequest updateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
        }

        User authenticatedUser = (User) authentication.getPrincipal();
        Employee employee = authenticatedUser.getEmployee();

        userService.updateUserProfile(employee, updateRequest);

        return ResponseEntity.ok("Profil mis à jour avec succès.");
    }


    @GetMapping("/{payrollId}")
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    public ResponseEntity<PayrollResponse> getPayrollById(@PathVariable Long payrollId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        PayrollResponse response = payrollService.getPayrollById(payrollId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-payroll")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<PayrollResponse>> getAllPayrolls() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<PayrollResponse> responses = payrollService.getAllPayrolls();
        return ResponseEntity.ok(responses);
    }
}
