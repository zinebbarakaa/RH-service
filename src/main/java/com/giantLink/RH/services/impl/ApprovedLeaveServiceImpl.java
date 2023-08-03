package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.ApprovedLeave;
import com.giantLink.RH.repositories.ApprovedLeaveRepository;
import com.giantLink.RH.services.ApprovedLeaveService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ApprovedLeaveServiceImpl implements ApprovedLeaveService {

    @Autowired
    ApprovedLeaveRepository approvedLeaveRepository;

    @Override
    public List<ApprovedLeave> getAll() {
        List<ApprovedLeave> list=approvedLeaveRepository.findAll();
        return list;
    }
}
