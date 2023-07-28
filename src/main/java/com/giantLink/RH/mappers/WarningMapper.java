package com.giantLink.RH.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.giantLink.RH.entities.Warning;
import com.giantLink.RH.models.request.WarningRequest;
import com.giantLink.RH.models.response.WarningResponse;

@Mapper(componentModel = "spring",
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface WarningMapper extends ApplicationMapper<WarningRequest, WarningResponse, Warning>{
	
	WarningMapper NSTANCE = Mappers.getMapper(WarningMapper.class);

}
