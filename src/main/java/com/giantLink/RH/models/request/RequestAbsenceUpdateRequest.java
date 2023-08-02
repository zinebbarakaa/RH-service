package com.giantLink.RH.models.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class RequestAbsenceUpdateRequest {

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
	private Long idEmployee;
	private boolean justification;
}
