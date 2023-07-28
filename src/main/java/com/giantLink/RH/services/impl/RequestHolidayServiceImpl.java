package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.mappers.RequestHolidayMapper;
import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.HolidayBalanceRepository;
import com.giantLink.RH.repositories.RequestHolidayRepository;
import com.giantLink.RH.repositories.RequestStatusRepository;
import com.giantLink.RH.services.RequestHolidayService;
import com.giantLink.RH.services.RequestStatusService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequestHolidayServiceImpl implements RequestHolidayService
{
    @Autowired
    private RequestHolidayRepository requestHolidayRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RequestStatusService requestStatusService;
    @Autowired
    private RequestStatusRepository requestStatusRepository;
    @Autowired
    private HolidayBalanceRepository holidayBalanceRepository;

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
    public RequestHolidayResponse get(Long aLong){
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
            RequestStatus requestStatus = RequestStatus.builder()
                    .type("Pending")
                    .request(requestHoliday)
                    .build();

            requestStatusRepository.save(requestStatus);
            requestHoliday.setStatus(requestStatus);
            requestHoliday.setEmployee(findEmployee.get());
            requestHolidayRepository.save(requestHoliday);

            RequestHolidayResponse response =  RequestHolidayMapper.INSTANCE.entityToResponse(requestHoliday);
//            response.setEmployee_id(findEmployee.get().getId());
//            response.setStatus_id(requestStatus.getId());
            return response;
        }
    }
    @Override
    public List<RequestHolidayResponse> getAllRequestHolidays() {
        List<RequestHoliday> requestHolidays = requestHolidayRepository.findAll();
        return RequestHolidayMapper.INSTANCE.listToResponseList(requestHolidays);
    }

    @Override
    public List<RequestHolidayResponse> getAllRequestHolidaysByIdEmployee(Long employee_id) {
        List<RequestHoliday> requestHolidays = requestHolidayRepository.findByEmployeeId(employee_id);
        return RequestHolidayMapper.INSTANCE.listToResponseList(requestHolidays);
    }

}
