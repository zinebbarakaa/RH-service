package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.repositories.RequestHolidayRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;


@Service
public class RequestHolidayServiceImpl {
    @Autowired
    private RequestHolidayRepository requestHolidayRepository;

    public void createRequestHoliday(RequestHoliday requestHoliday){

        RequestHoliday requestHoliday1 = RequestHoliday.builder().numberOfDays(requestHoliday.getNumberOfDays()).numberOfPaidLeaves(requestHoliday.getNumberOfPaidLeaves()).numberOfUnpaidLeaves(requestHoliday.getNumberOfUnpaidLeaves())
                .build();
        requestHolidayRepository.save(requestHoliday1);

    }
}
