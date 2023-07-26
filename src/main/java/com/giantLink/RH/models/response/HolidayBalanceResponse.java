package com.giantLink.RH.models.response;


import com.giantLink.RH.entities.Employee;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HolidayBalanceResponse
{
    private Long id;
    private int balance;
    private Date timestamp;
    private int holidayPerMonth;
    private Employee employee;
}
