package com.giantLink.RH.services.impl;


import com.giantLink.RH.entities.ApprovedLeave;
import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Request;
import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.enums.State;
import com.giantLink.RH.exceptions.ResourceNotFoundException;
import com.giantLink.RH.exceptions.RessourceNotFoundException;
import com.giantLink.RH.mappers.RequestStatusMapper;
import com.giantLink.RH.models.request.RequestAbsenceUpdateRequest;
import com.giantLink.RH.models.request.RequestStatusRequest;
import com.giantLink.RH.models.request.RequestStatusUpdateRequest;
import com.giantLink.RH.models.response.RequestStatusResponse;
import com.giantLink.RH.repositories.*;
import com.giantLink.RH.services.RequestStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class RequestStatusServiceImpl implements RequestStatusService {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    HolidayBalanceRepository holidayBalanceRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RequestHolidayRepository requestHolidayRepository;
    @Autowired
    RequestStatusRepository requestStatusRepository;

    @Autowired
    ApprovedLeaveRepository approvedLeaveRepository;

    @Override
    public RequestStatusResponse add(RequestStatusRequest request) {
        return null;
    }

    @Override

    public List<RequestStatusResponse> get() {

        if (requestStatusRepository.findAll().isEmpty()) {
            String message = messageSource.getMessage("requestStatus.notFound.message", null, "No Message", LocaleContextHolder.getLocale());
            throw new RuntimeException(message);
        } else {
            return RequestStatusMapper.INSTANCE.listToResponseList(requestStatusRepository.findAll());
        }
    }

    @Override
    public RequestStatusResponse update(RequestStatusRequest request, Long aLong) {

        return null;
    }

    @Override

    public void delete(Long id) {
        Optional<RequestStatus> requestStatus = requestStatusRepository.findById(id);
        if (requestStatus.isEmpty()) {
            String message = messageSource.getMessage("resource.notFound.message", new Object[]{"Request Status","Id", id}, "No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFoundException(message);
        } else {
            requestStatusRepository.deleteById(id);
        }
    }

    @Override
    public RequestStatusResponse get(Long requestStatusId) {
        Optional<RequestStatus> requestStatus = requestStatusRepository.findById(requestStatusId);
        if (requestStatus.isPresent()) {
            return RequestStatusMapper.INSTANCE.entityToResponse(requestStatus.get());
        } else {
            String message = messageSource.getMessage("resource.notFound.message", new Object[]{"request status", "Id", requestStatusId}, "No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public RequestStatusResponse processHolidayRequest(Long requestStatusId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Optional<RequestHoliday> requestHoliday = requestHolidayRepository.findByRequeStStatusId(requestStatusId);
        if (requestHoliday.isPresent()) {
            Employee employee = requestHoliday.get().getEmployee();
            int employeeHolidayBalance = employee.getHolidayBalance().getBalance();
            int requestedHolidays = requestHoliday.get().getNumberOfDays();
            Optional<RequestStatus> requestStatus = requestStatusRepository.findById(requestStatusId);

            if (employeeHolidayBalance < requestedHolidays) {
                requestStatus.get().setType(State.REFUSED);
                requestStatus.get().setMessageDetails(String.format("We regret to inform you that your holiday request has been refused due to insufficient holiday balance of %d days. Your current balance is %d days. Please adjust your holiday plans accordingly. If you have any questions, please contact HR.", requestHoliday.get().getNumberOfDays(), employee.getHolidayBalance().getBalance()));
                return RequestStatusMapper.INSTANCE.entityToResponse(requestStatusRepository.save(requestStatus.get()));
            } else {

                int updatedHolidayBalance = employeeHolidayBalance - requestedHolidays;
                employee.getHolidayBalance().setBalance(updatedHolidayBalance);
                employeeRepository.save(employee);
                requestStatus.get().setType(State.ACCEPTED);
                requestStatus.get().setMessageDetails(String.format("Your holiday request has been accepted. You have sufficient holiday balance to cover the requested duration. Enjoy your well-deserved time off from %s to %s. We hope you have a wonderful vacation!", dateFormat.format(requestHoliday.get().getStartDate()), dateFormat.format(requestHoliday.get().getFinishDate())));
                ////////------- Add an ApprovedLeave based on this request info -------////////
                ApprovedLeave approvedLeave=new ApprovedLeave();
                approvedLeave.setNumberOfDays((long) requestHoliday.get().getNumberOfDays());
                approvedLeave.setStartDate(requestHoliday.get().getStartDate());
                approvedLeave.setEndDate(requestHoliday.get().getFinishDate());
                approvedLeave.setEmployee(employee);
                approvedLeave.setReason("Sufficient holiday balance to cover the requested duration");
                approvedLeaveRepository.save(approvedLeave);
                //////// ----------------------------------------------------------------////////
                return RequestStatusMapper.INSTANCE.entityToResponse(requestStatusRepository.save(requestStatus.get()));
            }
        } else {
            String message = messageSource.getMessage("resource.notFound.message", new Object[]{"Request Holiday", "Id", requestStatusId}, "No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFoundException(message);

        }
    }

    @Override
    public List<RequestStatusResponse> getByStatusName(State state) {
        if (requestStatusRepository.findByType(state).get().isEmpty()) {
            String message = messageSource.getMessage("requestStatus.notFound.message", null, "No Message", LocaleContextHolder.getLocale());
            throw new RuntimeException(message);
        } else {
            return RequestStatusMapper.INSTANCE.listToResponseList(requestStatusRepository.findByType(state).get());
        }

    }

    @Override
    public RequestStatusResponse updateStatus(RequestStatusUpdateRequest requestStatusUpdateRequest, Long requestHolidayId) {
            Request requestHolidayById = requestHolidayRepository.findById(requestHolidayId).get();
            if (requestHolidayById == null) {
                String message = messageSource.getMessage("resource.notFound.message",new Object[]{"Request Holiday","Id",requestHolidayId},"No Message", LocaleContextHolder.getLocale());
                throw new RessourceNotFoundException(message);
            }
             Long requestStatusId =  requestHolidayById.getStatus().getId();

             if(requestStatusId == null) {
                 throw new ResourceNotFoundException("Request State Id Not Found");
             }
             Optional<RequestStatus> requestStatus = requestStatusRepository.findById(requestStatusId);
             if(!requestStatus.isPresent()) {
                 String message = messageSource.getMessage("resource.notFound.message",new Object[]{"Request Status","Id",requestStatusId},"No Message", LocaleContextHolder.getLocale());
                 throw new RessourceNotFoundException(message);
             }
            RequestStatus request = requestStatus.get();
            request.setType(requestStatusUpdateRequest.getType());
            request.setMessageDetails(requestStatusUpdateRequest.getMessageDetails());
            return(RequestStatusMapper.INSTANCE.entityToResponse(requestStatusRepository.save(request)));
        }
    }

