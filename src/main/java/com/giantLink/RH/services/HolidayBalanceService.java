package com.giantLink.RH.services;

import com.giantLink.RH.entities.HolidayBalance;
import com.giantLink.RH.models.request.HolidayBalanceRequest;
import com.giantLink.RH.models.response.HolidayBalanceResponse;
import com.giantLink.RH.models.response.RequestHolidayResponse;

public interface HolidayBalanceService extends CrudService<HolidayBalanceRequest, HolidayBalanceResponse, HolidayBalance, Long> {
}
