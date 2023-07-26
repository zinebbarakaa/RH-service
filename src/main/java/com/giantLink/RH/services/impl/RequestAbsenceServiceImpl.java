package com.giantLink.RH.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantLink.RH.models.request.RequestAbscenceUpdateRequest;
import com.giantLink.RH.models.request.RequestAbsenceRequest;
import com.giantLink.RH.models.response.RequestAbsenceResponse;
import com.giantLink.RH.repositories.RequestAbsenceRepository;
import com.giantLink.RH.services.RequestAbsenceService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RequestAbsenceServiceImpl implements RequestAbsenceService{
	
	@Autowired
	RequestAbsenceRepository requestAbsenceRepository ;

	@Override
	public RequestAbsenceResponse add(RequestAbsenceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestAbsenceResponse> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestAbsenceResponse update(RequestAbsenceRequest request, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RequestAbsenceResponse get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestAbsenceResponse updateJustification(RequestAbscenceUpdateRequest requestUpdate, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
