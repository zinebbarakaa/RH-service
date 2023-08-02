package com.giantLink.RH.services;

import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;

import java.util.List;

public interface RequestHolidayService extends CrudService<RequestHolidayRequest, RequestHolidayResponse, RequestHoliday,Long>
{
    List<RequestHolidayResponse> getAllRequestHolidays();

    List<RequestHolidayResponse> getAllRequestHolidaysByIdEmployee(Long employee_id);


}
