package com.giantLink.RH.models.response;

import java.util.Date;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.WarningType;

import lombok.Data;

@Data
public class WarningResponse {

	private Long id;

	private String messageDetails;
	
	private Date dateTime;
	
	private WarningType warningType;
	
	private Employee employee;
}
