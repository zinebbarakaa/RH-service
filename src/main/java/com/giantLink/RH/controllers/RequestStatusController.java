package com.giantLink.RH.controllers;

import com.giantLink.RH.enums.State;
import com.giantLink.RH.models.request.RequestStatusRequest;
import com.giantLink.RH.models.request.RequestStatusUpdateRequest;
import com.giantLink.RH.models.response.RequestStatusResponse;
import com.giantLink.RH.services.RequestStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/requestStatus")
public class RequestStatusController {
    @Autowired
    private RequestStatusService requestStatusService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RH')")
    public ResponseEntity<RequestStatusResponse> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(requestStatusService.get(id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN_RH')")
    public ResponseEntity<List<RequestStatusResponse>> get() {
        return new ResponseEntity<>(requestStatusService.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_RH')")
    public ResponseEntity delete(@PathVariable Long id) {
        requestStatusService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/state/{state}")
    @PreAuthorize("hasRole('ADMIN_RH')")
    public ResponseEntity<List<RequestStatusResponse>> get(@PathVariable("state") State state) {
        return new ResponseEntity<>(requestStatusService.getByStatusName(state), HttpStatus.OK);
    }

    @GetMapping("/processing/{id}")
    @PreAuthorize("hasRole('ADMIN_RH')")
    public ResponseEntity<RequestStatusResponse> processHolidayRequest(@PathVariable("id") Long id) {
        return new ResponseEntity<>(requestStatusService.processHolidayRequest(id), HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{id}")
    @PreAuthorize("hasRole('ADMIN_RH')")
    public ResponseEntity<RequestStatusResponse> updateStatus(@RequestBody RequestStatusUpdateRequest requestStatusUpdateRequest, @PathVariable("id") Long id) {
        return new ResponseEntity<>(requestStatusService.updateStatus(requestStatusUpdateRequest,id), HttpStatus.OK);
    }
}