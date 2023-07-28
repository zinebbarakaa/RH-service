package com.giantLink.RH.services.impl;


import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.HolidayBalance;
import com.giantLink.RH.exceptions.ResourceCantBeDeletedException;
import com.giantLink.RH.exceptions.ResourceDuplicatedException;
import com.giantLink.RH.exceptions.ResourceNotFoundException;
import com.giantLink.RH.mappers.EmployeeMapper;
import com.giantLink.RH.mappers.HolidayBalanceMapper;
import com.giantLink.RH.models.request.HolidayBalanceRequest;
import com.giantLink.RH.models.response.HolidayBalanceResponse;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.HolidayBalanceRepository;
import com.giantLink.RH.services.HolidayBalanceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HolidayBalanceServiceImpl implements HolidayBalanceService
{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private HolidayBalanceRepository holidayBalanceRepository;

    @Override

    public HolidayBalanceResponse add(HolidayBalanceRequest request)
    {
//        Create the employee
        HolidayBalance holidayBalance = HolidayBalanceMapper.INSTANCE.requestToEntity(request);
//        Save the holiday balance
        HolidayBalance holidayBalanceSaved = holidayBalanceRepository.save(holidayBalance);
//        Link the holiday balance to the employee
        if (request.getEmployeeId() != null)
        {
            Employee employee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("employee", "id", request.getEmployeeId().toString()));
            employee.setHolidayBalance(holidayBalanceSaved);
            employeeRepository.save(employee);
        }
//        Link the holiday balance to the employee
        return HolidayBalanceMapper.INSTANCE.entityToResponse(holidayBalanceSaved);
    }

    @Override
    public List<HolidayBalanceResponse> get()
    {
        List<HolidayBalance> holidayBalances = holidayBalanceRepository.findAll();
        return HolidayBalanceMapper.INSTANCE.listToResponseList(holidayBalances);
    }

    @Override
    public HolidayBalanceResponse update(HolidayBalanceRequest request, Long id)
    {
//        Check holiday balance exist
        HolidayBalance holidayBalance = holidayBalanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("holiday balance", "id", id.toString()));
//        Update entity
        if(request.getBalance() == 0) request.setBalance(holidayBalance.getBalance());
        if(request.getHolidayPerMonth() == 0) request.setHolidayPerMonth(holidayBalance.getHolidayPerMonth());
        HolidayBalanceMapper.INSTANCE.updateEntityFromRequest(request, holidayBalance);

//        Save changes
        holidayBalanceRepository.save(holidayBalance);
//        Prepare response
        return HolidayBalanceMapper.INSTANCE.entityToResponse(holidayBalance);
    }

    @Override
    public void delete(Long id)
    {
//        Check holiday balance exist
        HolidayBalance holidayBalance = holidayBalanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("holiday balance", "id", id.toString()));
//        Check if it is linked to an employee
        if (holidayBalance.getEmployee() != null)
            throw new ResourceCantBeDeletedException("holiday balance", "it is linked to an employee");
//        delete the holiday
        holidayBalanceRepository.deleteById(id);
    }

    @Override
    public HolidayBalanceResponse get(Long id)
    {
//        Check holiday balance exist
        HolidayBalance holidayBalance = holidayBalanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("holiday balance", "id", id.toString()));
//        Retrieve and return the holiday balance
        return HolidayBalanceMapper.INSTANCE.entityToResponse(holidayBalance);
    }
}
