package com.giantLink.RH.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestHoliday extends Request
{
    private int numberOfDays;

    private Date startDate;

    private Date finishDate;

    private Date returnDate;

    private Long numberOfPaidLeaves;

    private Long numberOfUnpaidLeaves;
}
