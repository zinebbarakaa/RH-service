package com.giantLink.RH.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Warning {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	

	private String messageDetails;
	
	@DateTimeFormat(style = "dd-mm-yyyy")
	private Date dateTime;
	
	@ManyToOne
	@JoinColumn(name="warningType_id")
	@JsonManagedReference
	private WarningType warningType;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	@JsonManagedReference
	private Employee employee;
	
	@PrePersist
	public void oncreate() {
		this.dateTime=new Date();
	}
	
}
