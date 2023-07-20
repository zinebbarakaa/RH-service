package com.giantLink.RH.services;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.response.EmployeeResponse;

public interface EmployeeService extends CrudService<EmployeeRequest, EmployeeResponse, Employee, Long> {

}
