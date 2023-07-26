package com.giantLink.RH.models.response;

import com.giantLink.RH.entities.Request;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHolidayResponse {
    private Long id;
    private int numberOfDays;
    private Date startDate;
    private Date finishDate;
    private Date returnDate;
    private int numberOfPaidLeaves;
    private Date numberOfUnpaidLeaves;
}
