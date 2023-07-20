package com.giantLink.RH.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.request.EmployeeRequest;
import com.giantLink.RH.response.EmployeeResponse;


@Mapper(componentModel="spring")
public interface EmployeeMapper extends ApplicationMapper<EmployeeRequest, EmployeeResponse, Employee> {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	
   
}