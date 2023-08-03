package com.giantLink.RH.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class Employee
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false ,length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, length = 15, unique = true)
	private String cin;

	@Column(length = 80, unique = true)
	private String email;

	@Column(length = 15)
	private String phone;

	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date recrutementDate;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JsonBackReference
	HolidayBalance holidayBalance;
	
	@OneToMany(mappedBy = "employee")
    @JsonBackReference
    private Set<Warning> warnings;

	@OneToMany(mappedBy = "employee")
    @JsonBackReference
    private Set<Request> requests;

	@Temporal(TemporalType.TIMESTAMP)

	@OneToOne (mappedBy = "employee", cascade = CascadeType.ALL)
	private User user;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<ApprovedLeave> approvedLeaves;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;    

	@PrePersist
	void setCreatedAtField(){
		createdAt = new Date();
	}
	@PreUpdate
	void setUpdatedAtField(){
		updatedAt = new Date();
	}
}
