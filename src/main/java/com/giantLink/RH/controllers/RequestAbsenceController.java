package com.giantLink.RH.controllers;

import com.giantLink.RH.models.request.RequestAbscenceUpdateRequest;
import com.giantLink.RH.models.request.RequestAbsenceRequest;
import com.giantLink.RH.models.response.RequestAbsenceResponse;
import com.giantLink.RH.services.RequestAbsenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<RequestAbsenceResponse>> getAllRequestAbsences() {
        List<RequestAbsenceResponse> responses = requestAbsenceService.get();
        if (!responses.isEmpty()) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestAbsenceResponse> getRequestAbsenceById(@PathVariable Long id) {
        RequestAbsenceResponse response = requestAbsenceService.get(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestAbsenceResponse> updateRequestAbsence(@PathVariable Long id, @RequestBody RequestAbsenceRequest request) {
        RequestAbsenceResponse response = requestAbsenceService.update(request, id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequestAbsence(@PathVariable Long id) {
        requestAbsenceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/justification/{id}")
    public ResponseEntity<RequestAbsenceResponse> updateJustification(@PathVariable Long id, @RequestBody RequestAbscenceUpdateRequest request) {
        RequestAbsenceResponse response = requestAbsenceService.updateJustification(request, id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
