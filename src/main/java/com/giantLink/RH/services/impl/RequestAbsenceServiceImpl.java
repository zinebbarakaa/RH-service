package com.giantLink.RH.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantLink.RH.entities.RequestAbsence;
import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.entities.Warning;
import com.giantLink.RH.entities.WarningType;
import com.giantLink.RH.mappers.RequestAbsenceMapper;
import com.giantLink.RH.models.request.RequestAbscenceUpdateRequest;
import com.giantLink.RH.models.request.RequestAbsenceRequest;
import com.giantLink.RH.models.response.RequestAbsenceResponse;
import com.giantLink.RH.repositories.RequestAbsenceRepository;
import com.giantLink.RH.repositories.WarningRepository;
import com.giantLink.RH.repositories.WarningTypeRepository;
import com.giantLink.RH.services.RequestAbsenceService;
import com.giantLink.RH.services.WarningService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RequestAbsenceServiceImpl implements RequestAbsenceService {

    @Autowired
    RequestAbsenceRepository requestAbsenceRepository;
    @Autowired
    WarningTypeRepository warningTypeRepository;
    @Autowired
    WarningRepository warningRepository;

    @Override
    public RequestAbsenceResponse add(RequestAbsenceRequest request) {
        RequestAbsence entity = RequestAbsenceMapper.INSTANCE.requestToEntity(request);
        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setType("Pending");
        requestStatus.setRequest(entity);
        entity.setStatus(requestStatus);
        if (request.getAbsenceDate() == null) {
            
            throw new IllegalArgumentException("La date d'absence ne peut pas être null.");
        }
        int dateCompare = entity.getAbsenceDate().compareTo(entity.getRequestDate());
        if(dateCompare<0) {
        	WarningType warningType = warningTypeRepository.findByTitle("Warning for Absence Without Request").get();
        	Warning warning = new Warning();
        	warning.setWarningType(warningType);
        	warning.setEmployee(entity.getEmployee());
        	warningRepository.save(warning);
        }
        return RequestAbsenceMapper.INSTANCE.entityToResponse(requestAbsenceRepository.save(entity));
    }

    @Override
    public List<RequestAbsenceResponse> get() {
        List<RequestAbsence> entities = requestAbsenceRepository.findAll();
        return RequestAbsenceMapper.INSTANCE.listToResponseList(entities);
    }

    @Override
    public RequestAbsenceResponse update(RequestAbsenceRequest request, Long id) {
        Optional<RequestAbsence> optionalEntity = requestAbsenceRepository.findById(id);
        if (optionalEntity.isPresent()) {
            RequestAbsence entity = optionalEntity.get();
            RequestAbsenceMapper.INSTANCE.updateEntityFromRequest(request, entity);
            RequestAbsence updatedEntity = requestAbsenceRepository.save(entity);
            return RequestAbsenceMapper.INSTANCE.entityToResponse(updatedEntity);
        }
        return null; // Gérer le cas où l'entité n'existe pas
    }

    @Override
    public void delete(Long id) {
        requestAbsenceRepository.deleteById(id);
    }

    @Override
    public RequestAbsenceResponse get(Long id) {
        Optional<RequestAbsence> optionalEntity = requestAbsenceRepository.findById(id);
        return optionalEntity.map(RequestAbsenceMapper.INSTANCE::entityToResponse).orElse(null);
    }

    @Override
    public RequestAbsenceResponse updateJustification(RequestAbscenceUpdateRequest requestUpdate, Long id) {
        Optional<RequestAbsence> optionalEntity = requestAbsenceRepository.findById(id);
        if (optionalEntity.isPresent()) {
            RequestAbsence entity = optionalEntity.get();
            // Mettre à jour l'attribut 'justification' de l'entité avec la valeur de la requête
            entity.setJustification(requestUpdate.isJustification());
            RequestAbsence updatedEntity = requestAbsenceRepository.save(entity);
            return RequestAbsenceMapper.INSTANCE.entityToResponse(updatedEntity);
        }
        return null; // Gérer le cas où l'entité n'existe pas
    }
}
