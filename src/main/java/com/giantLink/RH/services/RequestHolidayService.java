package com.giantLink.RH.services;

import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;

public interface RequestHolidayService extends CrudService<RequestHolidayRequest, RequestHolidayResponse, RequestHoliday,Long>
{

}
