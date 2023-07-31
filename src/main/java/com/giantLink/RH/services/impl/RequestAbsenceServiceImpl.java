package com.giantLink.RH.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.giantLink.RH.enums.State;
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
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.RequestAbsenceRepository;
import com.giantLink.RH.repositories.RequestStatusRepository;
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
    @Autowired
    RequestStatusRepository requestStatusRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public RequestAbsenceResponse add(RequestAbsenceRequest request) {
        if (request.getAbsenceDate() == null) {
            throw new IllegalArgumentException("La date d'absence ne peut pas être null.");
        }

        RequestAbsence entity = RequestAbsenceMapper.INSTANCE.requestToEntity(request);
        entity.setEmployee(employeeRepository.findById(request.getIdEmployee()).get());
       
        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setType(State.PENDING);
        requestStatus.setRequest(entity);
        if (entity.isSickness()== false) {
        	requestStatus.setMessageDetails(" Request for Absence");
        }
        else {
        	 requestStatus.setMessageDetails("Request for Sick Leave");
        }
        entity.setStatus(requestStatus);
        

        // Sauvegarder l'entité RequestStatus d'abord
        requestStatusRepository.save(requestStatus);
        
        RequestAbsenceResponse responce =RequestAbsenceMapper.INSTANCE.entityToResponse(requestAbsenceRepository.save(entity));
        responce.setMessage("Request added successfuly");
        if (entity.getRequestDate() != null) {
            int dateCompare = entity.getAbsenceDate().compareTo(entity.getRequestDate());
            if (dateCompare < 0) {
                WarningType warningType = warningTypeRepository.findByTitle("Warning for Absence Without Request").get();
                Warning warning = new Warning();
                warning.setWarningType(warningType);
                warning.setEmployee(entity.getEmployee());
                warningRepository.save(warning);
            }
        }
        
        return responce ;
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
            
            int compareDate = entity.getAbsenceDate().compareTo(updatedEntity.getAbsenceDate());
            if(compareDate != 0) {
            	 int dateCompare = entity.getAbsenceDate().compareTo(entity.getRequestDate());
                 if (dateCompare < 0) {
                     WarningType warningType = warningTypeRepository.findByTitle("Warning for Absence Without Request").get();
                     Warning warning = new Warning();
                     warning.setWarningType(warningType);
                     warning.setEmployee(entity.getEmployee());
                     warningRepository.save(warning);
                 }
            }
            
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
        return RequestAbsenceMapper.INSTANCE.entityToResponse(optionalEntity.get());
    }

    @Override
    public RequestAbsenceResponse updateJustification(RequestAbscenceUpdateRequest requestUpdate, Long id) {
        Optional<RequestAbsence> optionalEntity = requestAbsenceRepository.findById(id);
        if (optionalEntity.isPresent()) {
            RequestAbsence entity = optionalEntity.get();
            // Mettre à jour l'attribut 'justification' de l'entité avec la valeur de la requête
            entity.setJustification(requestUpdate.isJustification());
            RequestAbsence updatedEntity = requestAbsenceRepository.save(entity);
            warningRepository.findByEmployee(entity.getEmployee()).forEach(warning->{
            	long compareDate = warning.getCreatedAt().compareTo(entity.getAbsenceDate());
                if (compareDate == 1 && updatedEntity.isJustification() == true && warning.getWarningType()== warningTypeRepository.findByTitle("Unjustified Absence").get()) {
                	warningRepository.delete(warning);
                }
            });
            return RequestAbsenceMapper.INSTANCE.entityToResponse(updatedEntity);
        }
        return null; // Gérer le cas où l'entité n'existe pas
    }



	@Override
	public List<RequestAbsenceResponse> getIsSickness(boolean sickness) {
		 List<RequestAbsence> entities = requestAbsenceRepository.findBySickness(sickness);
	        return RequestAbsenceMapper.INSTANCE.listToResponseList(entities);
	}
}
