package com.giantLink.RH.models.response;

import lombok.Data;

import java.util.Date;

@Data
public class PayrollResponse {
    private Long id;
    private float salary;
    private Date paymentDate;
    private Long employeeId;
}
