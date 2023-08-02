package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.PredefinedHoliday;
import com.giantLink.RH.mappers.PredefinedHolidayMapper;
import com.giantLink.RH.models.request.PredefinedHolidayRequest;
import com.giantLink.RH.models.response.PredefinedHolidayResponse;
import com.giantLink.RH.repositories.PredefinedHolidayRepository;
import com.giantLink.RH.services.PredefinedHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PredefinedHolidayServiceImpl implements PredefinedHolidayService {

    @Autowired
    PredefinedHolidayRepository predefinedHolidayRepository;

    @Override
    public PredefinedHolidayResponse get(Long id) {
        Optional<PredefinedHoliday> optional=predefinedHolidayRepository.findById(id);
        if (optional.isPresent()){
            return PredefinedHolidayMapper.INSTANCE.entityToResponse(optional.get());
        }else{
            // Exception here
            return null;
        }

    }
    @Override
    public List<PredefinedHolidayResponse> get() {
        List<PredefinedHoliday> holidays=predefinedHolidayRepository.findAll();
        return PredefinedHolidayMapper.INSTANCE.listToResponseList(holidays);
    }
    @Override
    public PredefinedHolidayResponse add(PredefinedHolidayRequest request) {
        PredefinedHoliday holiday= PredefinedHolidayMapper.INSTANCE.requestToEntity(request);
        predefinedHolidayRepository.save(holiday);
        return PredefinedHolidayMapper.INSTANCE.entityToResponse(holiday);
    }
    @Override
    public PredefinedHolidayResponse update(PredefinedHolidayRequest request, Long id) {
        Optional<PredefinedHoliday> optional=predefinedHolidayRepository.findById(id);
        if (optional.isPresent()){
            optional.get().setNumberOfDays(request.getNumberOfDays());
            optional.get().setStartDate(request.getStartDate());
            optional.get().setEndDate(request.getEndDate());
            optional.get().setName(request.getName());
            optional.get().setDescription(request.getDescription());
            optional.get().setCountry(request.getCountry());
            predefinedHolidayRepository.save(optional.get());
            return PredefinedHolidayMapper.INSTANCE.entityToResponse(optional.get());
        }else{
            // Exception here
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        Optional<PredefinedHoliday> optional=predefinedHolidayRepository.findById(id);
        if (optional.isPresent()){
            predefinedHolidayRepository.delete(optional.get());
        }else{
            System.out.println("Exception on delete");
        }
    }


}
