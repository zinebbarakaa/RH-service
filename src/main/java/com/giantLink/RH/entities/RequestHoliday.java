package com.giantLink.RH.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHoliday extends Request{

    private int numberOfDays;
    private Date startDate;
    private Date finishDate;
    private Date returnDate;
    private int numberOfPaidLeaves;
    private int numberOfUnpaidLeaves;
}
