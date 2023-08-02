package com.giantLink.RH.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Warning;
import com.giantLink.RH.entities.WarningType;
import com.giantLink.RH.mappers.WarningMapper;
import com.giantLink.RH.models.request.WarningRequest;
import com.giantLink.RH.models.response.WarningResponse;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.WarningRepository;
import com.giantLink.RH.repositories.WarningTypeRepository;
import com.giantLink.RH.services.WarningService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WarningServiceImpl implements WarningService {

    @Autowired
    private WarningRepository warningRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WarningTypeRepository warningTypeRepository;
    
    @Autowired
    private WarningMapper warningMapper;

    @Override
    public WarningResponse add(WarningRequest request) {
        Warning warning = new Warning();
        warning.setMessageDetails(request.getMessageDetails());

        Optional<Employee> employee = employeeRepository.findById(request.getEmployeeId());
        if (employee.isPresent()) {
        warning.setEmployee(employee.get());

        Optional<WarningType> warningType = warningTypeRepository.findById(request.getWarningTypeId());
        if (warningType.isPresent()) {
          
        warning.setWarningType(warningType.get());

        warning = warningRepository.save(warning);
        return warningMapper.entityToResponse(warning);}
        else {
        	throw new RuntimeException("Invalid warning type");
        }
    }
        else {
        	throw new RuntimeException("Employee not found");
        }
    }

    @Override
    public List<WarningResponse> get() {
        List<Warning> warnings = warningRepository.findAll();
        return warningMapper.listToResponseList(warnings);
    }

    @Override
    public WarningResponse update(WarningRequest request, Long id) {
    	Optional<Warning> warningById = warningRepository.findById(id);
        if (warningById.isPresent()) {
        	Warning warning= warningById.get();
        warning.setMessageDetails(request.getMessageDetails());
        warning = warningRepository.save(warning);
        return warningMapper.entityToResponse(warning);}
        else {
        	throw new IllegalArgumentException("Invalid warningId");
        }
    }
    
    

    @Override
    public void delete(Long id) {
        warningRepository.deleteById(id);
    }

    @Override
    public WarningResponse get(Long id) {
    	Optional<Warning> warningById = warningRepository.findById(id);
        if (warningById.isPresent()) {
        	Warning warning= warningById.get();
        	return warningMapper.entityToResponse(warning);
        }
        else {
            throw new IllegalArgumentException("Invalid warningId");
        }
        
    }

    @Override
    public List<WarningResponse> getByEmployeeId(Long id) {
    	Optional<Employee> employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new IllegalArgumentException("Invalid employeeId");
        }
        List<Warning> warnings = warningRepository.findByEmployee(employee.get());
        return warningMapper.listToResponseList(warnings);
    }

    @Override
    public List<WarningResponse> getByWarningTypeId(Long id) {
    	Optional<WarningType> warningType = warningTypeRepository.findById(id);
        if (warningType == null) {
            throw new IllegalArgumentException("Invalid warningTypeId");
        }
        List<Warning> warnings = warningRepository.findByWarningType(warningType.get());
        return warningMapper.listToResponseList(warnings);
    }
}
