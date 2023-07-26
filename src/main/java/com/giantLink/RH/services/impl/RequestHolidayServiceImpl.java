package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.mappers.RequestHolidayMapper;
import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.RequestHolidayRepository;
import com.giantLink.RH.services.RequestHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestHolidayServiceImpl implements RequestHolidayService
{
    @Autowired
    private RequestHolidayRepository requestHolidayRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<RequestHolidayResponse> get()
    {
        return null;
    }

    @Override
    public RequestHolidayResponse update(RequestHolidayRequest request, Long aLong)
    {
        return null;
    }

    @Override
    public void delete(Long aLong)
    {

    }

    @Override
    public RequestHolidayResponse get(Long aLong)
    {
        return null;
    }

    @Override
    public RequestHolidayResponse add(RequestHolidayRequest requestHolidayRequest)
    {
        RequestHoliday requestHoliday = RequestHolidayMapper.INSTANCE.requestToEntity(requestHolidayRequest);
        Optional<Employee> findEmployee = employeeRepository.findById(requestHolidayRequest.getEmployee_id());
        if (!findEmployee.isPresent())
        {
            throw new RuntimeException("Employee having id : " + requestHolidayRequest.getEmployee_id().toString() + " is not found !");
        }
        else
        {

        }
        return null;
    }
}
