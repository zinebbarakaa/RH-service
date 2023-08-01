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

@Service
public class PredefinedHolidayServiceImpl implements PredefinedHolidayService {

    @Autowired
    PredefinedHolidayRepository predefinedHolidayRepository;

    @Override
    public PredefinedHolidayResponse add(PredefinedHolidayRequest request) {
        PredefinedHoliday holiday= PredefinedHolidayMapper.INSTANCE.requestToEntity(request);
        predefinedHolidayRepository.save(holiday);
        return PredefinedHolidayMapper.INSTANCE.entityToResponse(holiday);
    }

    @Override
    public List<PredefinedHolidayResponse> get() {
        List<PredefinedHoliday> holidays=predefinedHolidayRepository.findAll();
        return PredefinedHolidayMapper.INSTANCE.listToResponseList(holidays);
    }

    @Override
    public PredefinedHolidayResponse update(PredefinedHolidayRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public PredefinedHolidayResponse get(Long aLong) {
        return null;
    }
}
