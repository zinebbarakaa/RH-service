package com.giantLink.RH.models.response;

import java.util.Date;

import lombok.Data;

@Data
public class WarningTypeResponse {

private Long id;
	
	private String title;
	
	private String description;
	
	private Date createAt;
	
}
