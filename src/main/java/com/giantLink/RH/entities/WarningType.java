package com.giantLink.RH.entities;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarningType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "warningType")
    @JsonBackReference
    private Set<Warning> warnings;
	
	private String title;
	
	private String description;
	
	@DateTimeFormat(style = "dd-mm-yyyy HH:mm")
	private Date createdAt;
	
	@DateTimeFormat(style = "dd-mm-yyyy HH:mm")
	private Date updatedAt;
	
	@PrePersist
	public void onCreate() {
		this.createdAt= new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt=new Date();
	}

	public WarningType(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
	
}
