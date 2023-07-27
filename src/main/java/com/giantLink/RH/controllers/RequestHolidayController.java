package com.giantLink.RH.controllers;

import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;
import com.giantLink.RH.services.RequestHolidayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/request-holidays")
public class RequestHolidayController
{
    @Autowired
    RequestHolidayService requestHolidayService;
    @PostMapping
    public ResponseEntity<RequestHolidayResponse> addRequestHoliday(@Valid @RequestBody RequestHolidayRequest requestHolidayRequest)
    {
        RequestHolidayResponse response = requestHolidayService.add(requestHolidayRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
