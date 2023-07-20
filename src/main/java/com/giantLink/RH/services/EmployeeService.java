package com.giantLink.RH.services;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.request.EmployeeRequest;
import com.giantLink.RH.response.EmployeeResponse;

public interface EmployeeService extends CrudService<EmployeeRequest, EmployeeResponse, Employee, Long> {

}
