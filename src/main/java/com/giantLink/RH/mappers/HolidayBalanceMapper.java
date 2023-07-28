package com.giantLink.RH.mappers;

import com.giantLink.RH.entities.HolidayBalance;
import com.giantLink.RH.models.request.HolidayBalanceRequest;
import com.giantLink.RH.models.response.HolidayBalanceResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface HolidayBalanceMapper extends ApplicationMapper<HolidayBalanceRequest, HolidayBalanceResponse, HolidayBalance>{
    HolidayBalanceMapper INSTANCE = Mappers.getMapper(HolidayBalanceMapper.class);
}
