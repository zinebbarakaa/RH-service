package com.giantLink.RH.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RequestHolidayResponse
{
    private Long id;

    private int numberofdays;


    @JsonFormat(pattern="dd-MM-yyyy")
    private Date startDate;


    @JsonFormat(pattern="dd-MM-yyyy")
    private Date finishDate;


    @JsonFormat(pattern="dd-MM-yyyy")
    private Date returnDate;

    private int numberOfPaidLeaves;

    private int numberOfUnpaidLeaves;

}
