package com.giantLink.RH.controllers;

import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.request.HolidayBalanceRequest;
import com.giantLink.RH.models.response.EmployeeResponse;
import com.giantLink.RH.models.response.HolidayBalanceResponse;
import com.giantLink.RH.models.response.SuccessResponse;
import com.giantLink.RH.repositories.HolidayBalanceRepository;
import com.giantLink.RH.services.EmployeeService;
import com.giantLink.RH.services.HolidayBalanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holidaybalances")
public class HolidayBalanceController {
    @Autowired
    private HolidayBalanceService holidayBalanceService;

    @PostMapping
    public ResponseEntity<HolidayBalanceResponse> addHolidayBalance(@RequestBody @Valid HolidayBalanceRequest request) {
        HolidayBalanceResponse holidayBalanceResponse = holidayBalanceService.add(request);
        return new ResponseEntity<>(holidayBalanceResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HolidayBalanceResponse>> getAllHolidayBalances() {
        List<HolidayBalanceResponse> holidayBalances = holidayBalanceService.get();
        return new ResponseEntity<>(holidayBalances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HolidayBalanceResponse> getHolidayBalanceById(@PathVariable Long id) {
        HolidayBalanceResponse holidayBalanceResponse = holidayBalanceService.get(id);
        return new ResponseEntity<>(holidayBalanceResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HolidayBalanceResponse> updateHolidayBalance(@PathVariable Long id, @RequestBody HolidayBalanceRequest request) {
        HolidayBalanceResponse holidayBalanceResponse = holidayBalanceService.update(request, id);
        return new ResponseEntity<>(holidayBalanceResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteEmployee(@PathVariable Long id) {
        holidayBalanceService.delete(id);
        SuccessResponse successResponse = SuccessResponse.builder()
                .message("Holiday balance deleted successfully")
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
