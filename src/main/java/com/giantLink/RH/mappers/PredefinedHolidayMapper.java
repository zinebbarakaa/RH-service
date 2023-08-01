package com.giantLink.RH.mappers;

import com.giantLink.RH.entities.PredefinedHoliday;
import com.giantLink.RH.models.request.PredefinedHolidayRequest;
import com.giantLink.RH.models.response.PredefinedHolidayResponse;
import org.mapstruct.factory.Mappers;

public interface PredefinedHolidayMapper extends ApplicationMapper<PredefinedHolidayRequest, PredefinedHolidayResponse, PredefinedHoliday>{

    PredefinedHolidayMapper INSTANCE = Mappers.getMapper(PredefinedHolidayMapper.class);
}
