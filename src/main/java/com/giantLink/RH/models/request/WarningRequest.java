package com.giantLink.RH.models.request;

import java.util.Date;

import lombok.Data;

@Data
public class WarningRequest {	

	private String messageDetails;
	
	private Long warningTypeId;
	
	private Long employeeId;

}
