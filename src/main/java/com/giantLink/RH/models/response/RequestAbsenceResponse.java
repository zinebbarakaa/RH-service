package com.giantLink.RH.models.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.giantLink.RH.entities.Employee;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class RequestAbsenceResponse {
	
	private Long id;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date absenceDate;
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(style = "HH:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private Date exitTime;
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(style = "HH:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private Date entryTime;
	private boolean sickness;
	private String reason;
	private String justfication;
	private Employee employee;
	private String message;

}
