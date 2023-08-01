package com.giantLink.RH.controllers;

import com.giantLink.RH.models.response.PredefinedHolidayResponse;
import com.giantLink.RH.services.PredefinedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/holiday")
public class HolidayController {

    @Autowired
    PredefinedHolidayService predefinedHolidayService;

    @GetMapping("/predefined-holidays")
    public ResponseEntity<List<PredefinedHolidayResponse>> getPredefineds(){
        return new ResponseEntity<>(predefinedHolidayService.get(), HttpStatus.OK);
    }
}
