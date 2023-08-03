package com.giantLink.RH.controllers;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.User;
import com.giantLink.RH.models.request.PayrollRequest;
import com.giantLink.RH.models.response.PayrollResponse;
import com.giantLink.RH.services.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payrolle")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN_RH')")
    public ResponseEntity<PayrollResponse> createPayroll(@RequestBody PayrollRequest payrollRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        PayrollResponse response = payrollService.createPayroll(payrollRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{payrollId}")
    @PreAuthorize("hasRole('ADMIN_RH')")
    public ResponseEntity<PayrollResponse> updatePayroll(@PathVariable Long payrollId, @RequestBody PayrollRequest payrollRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        PayrollResponse response = payrollService.updatePayroll(payrollId, payrollRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{payrollId}")
    @PreAuthorize("hasAnyRole('ADMIN_RH')")
    public ResponseEntity<PayrollResponse> getPayrollById(@PathVariable Long payrollId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        PayrollResponse response = payrollService.getPayrollById(payrollId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN_RH')")
    public ResponseEntity<List<PayrollResponse>> getAllPayrolls() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<PayrollResponse> responses = payrollService.getAllPayrolls();
        return ResponseEntity.ok(responses);
    }
}
