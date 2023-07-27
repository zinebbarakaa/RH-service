package com.giantLink.RH.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giantLink.RH.models.request.WarningRequest;
import com.giantLink.RH.models.response.WarningResponse;
import com.giantLink.RH.services.WarningService;

@RestController
@RequestMapping("/warnings")
public class WarningController {

    @Autowired
    private WarningService warningService;

    @PostMapping
    public ResponseEntity<WarningResponse> addWarning(@RequestBody WarningRequest request) {
        WarningResponse response = warningService.add(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WarningResponse>> getAllWarnings() {
        List<WarningResponse> responseList = warningService.get();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarningResponse> getWarningById(@PathVariable Long id) {
        WarningResponse response = warningService.get(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarningResponse> updateWarning(@PathVariable Long id, @RequestBody WarningRequest request) {
        WarningResponse response = warningService.update(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarning(@PathVariable Long id) {
        warningService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<WarningResponse>> getWarningsByEmployeeId(@PathVariable Long id) {
        List<WarningResponse> responseList = warningService.getByEmployeeId(id);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/warningType/{id}")
    public ResponseEntity<List<WarningResponse>> getWarningsByWarningTypeId(@PathVariable Long id) {
        List<WarningResponse> responseList = warningService.getByWarningTypeId(id);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
