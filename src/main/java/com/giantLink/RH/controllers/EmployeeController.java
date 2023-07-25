package com.giantLink.RH.controllers;
import java.util.List;

import com.giantLink.RH.models.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.response.EmployeeResponse;
import com.giantLink.RH.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody @Valid EmployeeRequest request) {
        EmployeeResponse employeeResponse = employeeService.add(request);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.get();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse employee = employeeService.get(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest request) {
        EmployeeResponse updatedEmployee = employeeService.update(request, id);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        SuccessResponse successResponse = SuccessResponse.builder()
                .message("Employee deleted successfully")
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.NO_CONTENT);
    }
}
