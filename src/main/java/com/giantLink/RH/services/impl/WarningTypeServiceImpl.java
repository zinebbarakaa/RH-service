package com.giantLink.RH.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantLink.RH.entities.WarningType;
import com.giantLink.RH.mappers.WarningTypeMapper;
import com.giantLink.RH.models.request.WarningTypeRequest;
import com.giantLink.RH.models.response.WarningTypeResponse;
import com.giantLink.RH.repositories.WarningTypeRepository;
import com.giantLink.RH.services.WarningTypeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WarningTypeServiceImpl implements WarningTypeService {

    @Autowired
    WarningTypeRepository warningTypeRepository;
    
    @Autowired
    WarningTypeMapper warningTypeMapper;

    @Override
    public WarningTypeResponse add(WarningTypeRequest request) {
        WarningType warningType = warningTypeMapper.requestToEntity(request);
        warningType = warningTypeRepository.save(warningType);
        return warningTypeMapper.entityToResponse(warningType);
    }

    @Override
    public List<WarningTypeResponse> get() {
        List<WarningType> warningTypes = warningTypeRepository.findAll();
        return warningTypes.stream()
                .map(warningTypeMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WarningTypeResponse update(WarningTypeRequest request, Long id) {
        Optional<WarningType> existingWarningType = warningTypeRepository.findById(id);
        if (existingWarningType.isPresent()) {
          
        // Mettre à jour les attributs du WarningType existant
        existingWarningType.get().setTitle(request.getTitle());
        existingWarningType.get().setDescription(request.getDescription());
        
        warningTypeRepository.save(existingWarningType.get());
        return warningTypeMapper.entityToResponse(existingWarningType.get());}
        else {
        	throw new RuntimeException("warning type not found");
        }
    }

    @Override
    public void delete(Long id) {
    	Optional<WarningType> warningType = warningTypeRepository.findById(id);
        if(warningType.isPresent()) {
        warningTypeRepository.deleteById(id);
        }
        else {
        	throw new RuntimeException("warning type not found");
        }
    }

    @Override
    public WarningTypeResponse get(Long id) {
        Optional<WarningType> warningType = warningTypeRepository.findById(id);
        if(warningType.isPresent()) {
    
        return warningTypeMapper.entityToResponse(warningType.get());
        }
        else {
        	throw new RuntimeException("warning type not found");
        }
    }
}
