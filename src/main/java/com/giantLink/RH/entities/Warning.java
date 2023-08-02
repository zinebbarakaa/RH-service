package com.giantLink.RH.entities;

import java.util.Date;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="warningType_id")
	@JsonManagedReference
	private WarningType warningType;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="employee_id")
	@JsonManagedReference
	private Employee employee;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	
	@PrePersist
	public void oncreate() {
		this.createdAt=new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}

	
}
