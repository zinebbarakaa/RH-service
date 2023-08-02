package com.giantLink.RH.controllers;

import com.giantLink.RH.entities.ApprovedLeave;
import com.giantLink.RH.models.request.PredefinedHolidayRequest;
import com.giantLink.RH.models.response.PredefinedHolidayResponse;
import com.giantLink.RH.services.ApprovedLeaveService;
import com.giantLink.RH.services.PredefinedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN_RH')")
@RequestMapping("api/holiday")
public class HolidayController {

    @Autowired
    PredefinedHolidayService predefinedHolidayService;
    @Autowired
    ApprovedLeaveService approvedLeaveService;

    @PreAuthorize("hasAuthority('ADMIN_READ')")
    @GetMapping("/predefined-holidays")
    public ResponseEntity<List<PredefinedHolidayResponse>> getPredefined(){
        return new ResponseEntity<>(predefinedHolidayService.get(), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    @GetMapping("/predefined-holidays/{id}")
    public ResponseEntity<PredefinedHolidayResponse> getPredefinedById(@PathVariable Long id){
        return new ResponseEntity<>(predefinedHolidayService.get(id), HttpStatus.OK);
    }
    @PostMapping("/predefined-holidays")
    public ResponseEntity<PredefinedHolidayResponse> addPredefined(@RequestBody PredefinedHolidayRequest request){
        return new ResponseEntity<>(predefinedHolidayService.add(request), HttpStatus.CREATED);
    }
    @PutMapping("/predefined-holidays/{id}")
    public ResponseEntity<PredefinedHolidayResponse> updatePredefined(@RequestBody PredefinedHolidayRequest request, @PathVariable Long id){
        return new ResponseEntity<>(predefinedHolidayService.update(request,id), HttpStatus.OK);
    }
    @DeleteMapping("/predefined-holidays/{id}")
    public ResponseEntity<String> deletePredefined(@PathVariable Long id){
        predefinedHolidayService.delete(id);
        return new ResponseEntity<>("Done",HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN_READ')")
    @GetMapping("/approved-leaves")
    public ResponseEntity<List<ApprovedLeave>> getLeaves(){
        return new ResponseEntity<>(approvedLeaveService.getAll(),HttpStatus.OK);
    }
}
