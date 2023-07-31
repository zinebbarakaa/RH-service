package com.giantLink.RH.services;

import java.util.List;

import com.giantLink.RH.entities.RequestAbsence;
import com.giantLink.RH.models.request.RequestAbsenceUpdateRequest;
import com.giantLink.RH.models.request.RequestAbsenceRequest;
import com.giantLink.RH.models.response.RequestAbsenceResponse;

public interface RequestAbsenceService extends CrudService<RequestAbsenceRequest,RequestAbsenceResponse,RequestAbsence,Long> {
	
	public RequestAbsenceResponse updateJustification(RequestAbsenceUpdateRequest requestUpdate,Long id);
	public List<RequestAbsenceResponse> getIsSickness(boolean sickness);
	public List<RequestAbsenceResponse> getRequestAbsenceByEmployeeId(Long id);
	public List<RequestAbsenceResponse> getByEmployeeIsSickness(boolean sickness , Long id);

}
