package com.giantLink.RH.services;

import com.giantLink.RH.entities.RequestAbsence;
import com.giantLink.RH.models.request.RequestAbscenceUpdateRequest;
import com.giantLink.RH.models.request.RequestAbsenceRequest;
import com.giantLink.RH.models.response.RequestAbsenceResponse;

public interface RequestAbsenceService extends CrudService<RequestAbsenceRequest,RequestAbsenceResponse,RequestAbsence,Long> {
	
	public RequestAbsenceResponse updateJustification(RequestAbscenceUpdateRequest requestUpdate,Long id);

}