package com.giantLink.RH.controllers;

import com.giantLink.RH.models.request.RequestAbsenceRequest;
import com.giantLink.RH.models.response.RequestAbsenceResponse;
import com.giantLink.RH.services.RequestAbsenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/absences")
public class RequestAbsenceController {

    private final RequestAbsenceService requestAbsenceService;

    @Autowired
    public RequestAbsenceController(RequestAbsenceService requestAbsenceService) {
        this.requestAbsenceService = requestAbsenceService;
    }

    @PostMapping("/add")
    public ResponseEntity<RequestAbsenceResponse> addRequestAbsence(@RequestBody RequestAbsenceRequest request) {
        RequestAbsenceResponse response = requestAbsenceService.add(request);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
