package com.giantLink.RH.models.response;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.RequestStatus;

import lombok.Data;

@Data
public class RequestAbsenceResponse {	
	
	private Employee employee;
	private RequestStatus status;
	private String message;

}
