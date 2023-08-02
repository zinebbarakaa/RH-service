package com.giantLink.RH.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.giantLink.RH.entities.RequestAbsence;
import com.giantLink.RH.models.request.RequestAbsenceRequest;
import com.giantLink.RH.models.response.RequestAbsenceResponse;

@Mapper(componentModel = "spring",
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface RequestAbsenceMapper extends ApplicationMapper<RequestAbsenceRequest, RequestAbsenceResponse, RequestAbsence> {
	
	RequestAbsenceMapper INSTANCE = Mappers.getMapper(RequestAbsenceMapper.class);

}
