package com.giantLink.RH.services;

import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.models.request.RequestStatusRequest;
import com.giantLink.RH.models.response.RequestStatusResponse;

public interface RequestStatusService extends CrudService<RequestStatusRequest, RequestStatusResponse,RequestStatus,Long>
{

}
