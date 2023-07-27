package com.giantLink.RH.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.giantLink.RH.entities.WarningType;
import com.giantLink.RH.models.request.WarningTypeRequest;
import com.giantLink.RH.models.response.WarningTypeResponse;

@Mapper(componentModel = "spring",
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface WarningTypeMapper extends ApplicationMapper<WarningTypeRequest, WarningTypeResponse, WarningType>{
	
	WarningTypeMapper INSTANCE = Mappers.getMapper(WarningTypeMapper.class);

}
