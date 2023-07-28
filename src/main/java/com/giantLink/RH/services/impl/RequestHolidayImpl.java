package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;
import com.giantLink.RH.services.CrudService;

import java.util.List;

public class RequestHolidayImpl implements CrudService<RequestHolidayRequest, RequestHolidayResponse, RequestHoliday, Long> {
    @Override
    public RequestHolidayResponse add(RequestHolidayRequest request) {

        return null;
    }

    @Override
    public List<RequestHolidayResponse> get() {
        return null;
    }

    @Override
    public RequestHolidayResponse update(RequestHolidayRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public RequestHolidayResponse get(Long aLong) {
        return null;
    }
}