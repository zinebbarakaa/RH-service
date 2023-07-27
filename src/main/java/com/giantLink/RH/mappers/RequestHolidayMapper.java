package com.giantLink.RH.mappers;

import com.giantLink.RH.entities.Request;
import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RequestHolidayMapper extends ApplicationMapper<RequestHolidayRequest, RequestHolidayResponse, RequestHoliday>
{
    RequestHolidayMapper INSTANCE = Mappers.getMapper(RequestHolidayMapper.class);

}
