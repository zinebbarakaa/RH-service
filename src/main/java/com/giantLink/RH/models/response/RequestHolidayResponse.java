package com.giantLink.RH.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.giantLink.RH.entities.RequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestHolidayResponse
{
    private Long id;
    private int numberOfDays;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date finishDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date returnDate;
    private Long status_id;
    private Long employee_id;
    private int numberOfPaidLeaves;
    private int numberOfUnpaidLeaves;
}
