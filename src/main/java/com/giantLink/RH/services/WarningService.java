package com.giantLink.RH.services;

import java.util.List;

import com.giantLink.RH.entities.Warning;
import com.giantLink.RH.models.request.WarningRequest;
import com.giantLink.RH.models.response.WarningResponse;

public interface WarningService extends CrudService<WarningRequest, WarningResponse, Warning, Long>{
	
	public List<WarningResponse> getByEmployeeId(Long id);
	
	public List<WarningResponse> getByWarningTypeId(Long id);

}
