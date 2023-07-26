package com.giantLink.RH.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giantLink.RH.services.RequestAbsenceService;

@RestController
@RequestMapping("/absence")
public class RequestAbsenceController {

	@Autowired
	RequestAbsenceService requestAbsenceService;
}
