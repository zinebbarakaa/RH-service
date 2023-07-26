package com.giantLink.RH.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.response.EmployeeResponse;



@Mapper(componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
		nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface EmployeeMapper extends ApplicationMapper<EmployeeRequest, EmployeeResponse, Employee>
{
	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

}