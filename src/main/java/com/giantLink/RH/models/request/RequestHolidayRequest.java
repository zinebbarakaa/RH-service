package com.giantLink.RH.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestHolidayRequest {
    private int numberOfDays;
    private Date startDate;
    private Date finishDate;
    private Date returnDate;
    private int numberOfPaidLeaves;
    private int numberOfUnpaidLeaves;
    private Long employee_id;
    private Long requestStatus_Id;
}
