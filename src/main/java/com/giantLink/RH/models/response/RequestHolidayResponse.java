package com.giantLink.RH.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.RequestStatus;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class RequestHolidayResponse
{
    private Long id;
    private int numberOfDays;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date requestDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date finishDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date returnDate;
    private RequestStatus status;
    private Employee employee;
    private int numberOfPaidLeaves;
    private int numberOfUnpaidLeaves;
    private Date updatedAt;
    private Date createdAt;
}
