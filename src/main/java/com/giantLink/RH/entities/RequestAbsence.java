package com.giantLink.RH.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestAbsence extends Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy" )
	private Date absenceDate;
	@Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "HH:mm")
	private Date exitTime;
	@Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "HH:mm")
	private Date entryTime;
	private boolean sickness;
	private String reason;
	private boolean justification;
	
	

	
}
