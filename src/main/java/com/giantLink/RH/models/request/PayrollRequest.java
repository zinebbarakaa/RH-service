package com.giantLink.RH.models.request;

import lombok.Data;

import java.util.Date;

@Data
public class PayrollRequest {
    private float salary;
    private Date paymentDate;
    private Long employeeId;
}
