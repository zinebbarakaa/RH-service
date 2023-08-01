package com.giantLink.RH.models.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class PredefinedHolidayRequest {

    private Long numberOfDays;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String name;
    private String country;
    private String description;
}
