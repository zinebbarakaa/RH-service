package com.giantLink.RH.services.impl;
import java.util.List;
import java.util.Optional;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.HolidayBalance;
import com.giantLink.RH.exceptions.ResourceDuplicatedException;
import com.giantLink.RH.mappers.EmployeeMapper;
import com.giantLink.RH.mappers.HolidayBalanceMapper;
import com.giantLink.RH.repositories.HolidayBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.services.EmployeeService;

import jakarta.transaction.Transactional;

import com.giantLink.RH.exceptions.ResourceNotFoundException;
import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.response.EmployeeResponse;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private HolidayBalanceRepository holidayBalanceRepository;

    @Override
    public EmployeeResponse add(EmployeeRequest request) {
//        Check unique properties are not duplicated
        if (request.getCin() != null && employeeRepository.findByCin(request.getCin()).isPresent())
            throw new ResourceDuplicatedException("employee", "cin", request.getCin());
        if (request.getEmail() != null && employeeRepository.findByEmail(request.getEmail()).isPresent())
            throw new ResourceDuplicatedException("employee", "email", request.getEmail());
//        Create the employee
        Employee employee = EmployeeMapper.INSTANCE.requestToEntity(request);
//        Create holiday balance of the employee
        HolidayBalance holidayBalance = new HolidayBalance();
//        Get holiday balance info if exist
        if(request.getHolidayBalance() != null){
            HolidayBalanceMapper.INSTANCE.updateEntityFromRequest(request.getHolidayBalance(), holidayBalance);
        }
//        Save the holiday balance
        holidayBalanceRepository.save(holidayBalance);
        employee.setHolidayBalance(holidayBalance);
//        Save the employee
        employeeRepository.save(employee);

//        Prepare and return the response
        return EmployeeMapper.INSTANCE.entityToResponse(employee);
    }

    @Override
    public List<EmployeeResponse> get() {
        List<Employee> employees = employeeRepository.findAll();
        return EmployeeMapper.INSTANCE.listToResponseList(employees);
    }

    @Override
    public EmployeeResponse update(EmployeeRequest request, Long id) {
//        Check if the employee exists
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", id.toString()));
//        Check unique properties are not duplicated
        if (request.getCin() != null){
            Optional<Employee> employeeByCin = employeeRepository.findByCin(request.getCin());
            if (employeeByCin.isPresent() && !employeeByCin.get().getId().equals(id))
                throw new ResourceDuplicatedException("employee", "cin", request.getCin());
        }
        if (request.getEmail() != null){
            Optional<Employee> employeeByEmail = employeeRepository.findByEmail(request.getEmail());
            if (employeeByEmail.isPresent() && !employeeByEmail.get().getId().equals(id))
                throw new ResourceDuplicatedException("employee", "email", request.getEmail());
        }
//        Update entity
        EmployeeMapper.INSTANCE.updateEntityFromRequest(request, employee);
//        Save changes
        employeeRepository.save(employee);
//        Prepare and return the response
        return EmployeeMapper.INSTANCE.entityToResponse(employee);
    }

    @Override
    public void delete(Long id) {
//        Check if the employee exists
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", id.toString()));
//        Delete entity
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeResponse get(Long id) {
//        Check if the employee exists
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", id.toString()));
//        Prepare and return the response
        return EmployeeMapper.INSTANCE.entityToResponse(employee);
    }

    @Override
    public EmployeeResponse detachHolidayBalanceFromEmployee(Long id) {
//        Check if the employee exists
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", id.toString()));
        employee.setHolidayBalance(null);
        employeeRepository.save(employee);

        return EmployeeMapper.INSTANCE.entityToResponse(employee);
    }
}
