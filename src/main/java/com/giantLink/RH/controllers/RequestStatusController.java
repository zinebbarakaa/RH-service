package com.giantLink.RH.controllers;

import com.giantLink.RH.enums.State;
import com.giantLink.RH.models.response.RequestStatusResponse;
import com.giantLink.RH.services.RequestStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/requestStatus")
public class RequestStatusController {
    @Autowired
    private RequestStatusService requestStatusService;

    @GetMapping("/{id}")
    public ResponseEntity<RequestStatusResponse> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(requestStatusService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RequestStatusResponse>> get() {
        return new ResponseEntity<>(requestStatusService.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        requestStatusService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<RequestStatusResponse>> get(@PathVariable("state") State state) {
        return new ResponseEntity<>(requestStatusService.getByStatusName(state), HttpStatus.OK);
    }

    @GetMapping("/processing/{id}")
    public ResponseEntity<RequestStatusResponse> processHolidayRequest(@PathVariable("id") Long id) {
        return new ResponseEntity<>(requestStatusService.processHolidayRequest(id), HttpStatus.OK);
    }
}