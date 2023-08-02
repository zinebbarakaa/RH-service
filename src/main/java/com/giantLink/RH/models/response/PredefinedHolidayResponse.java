package com.giantLink.RH.models.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredefinedHolidayResponse {

    private Long numberOfDays;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date endDate;
    private String name;
    private String country;
    private String description;
}
