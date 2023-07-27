package com.giantLink.RH.models.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HolidayBalanceRequest
{
    private Long id;

    @NotBlank
    @Min(0)
    private int balance;

    @NotBlank
    private Date timestamp;

    @NotBlank
    @Min(0)
    private int holidayPerMonth;

    private Long employeeId;
}
