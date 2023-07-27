package com.giantLink.RH.services;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.enums.State;
import com.giantLink.RH.exceptions.GLHolidayBalanceIsufficient;
import com.giantLink.RH.models.request.EmployeeRequest;
import com.giantLink.RH.models.request.RequestStatusRequest;
import com.giantLink.RH.models.response.EmployeeResponse;
import com.giantLink.RH.models.response.RequestStatusResponse;

import java.util.List;
import java.util.Optional;


public interface RequestStatusService extends CrudService<RequestStatusRequest, RequestStatusResponse, RequestStatus, Long> {
    RequestStatusResponse processHolidayRequest(Long requestStatusId);

    List<RequestStatusResponse> getByStatusName(State state);
}
