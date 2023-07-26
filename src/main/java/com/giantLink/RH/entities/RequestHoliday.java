package com.giantLink.RH.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHoliday extends Request
{
    private Long numberOfDays;

    private Date startDate;

    private Date finishDate;

    private Date returnDate;

    private Long numberOfPaidLeaves;

    private Long numberOfUnpaidLeaves;
}
