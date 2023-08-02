package com.giantLink.RH.mappers;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Payroll;
import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.request.PayrollRequest;
import com.giantLink.RH.models.response.EmployeeResponse;
import com.giantLink.RH.models.response.PayrollResponse;
import org.mapstruct.factory.Mappers;

public interface PayrollMapper extends ApplicationMapper<PayrollRequest, PayrollResponse, Payroll>
{
    PayrollMapper INSTANCE = Mappers.getMapper(PayrollMapper.class);
}
