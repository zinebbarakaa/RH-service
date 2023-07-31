package com.giantLink.RH.models.response;

import com.giantLink.RH.entities.HolidayBalance;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeeResponse
{
	private Long id;
	private String firstName;
	private String lastName;
	private String cin;
	private String email;
	private String phone;
	private Date recrutementDate;
	private HolidayBalance holidayBalance;
	private Date updatedAt;
	private Date createdAt;

}
