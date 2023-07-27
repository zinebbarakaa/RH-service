package com.giantLink.RH.services.impl;

import com.giantLink.RH.models.request.RequestStatusRequest;
import com.giantLink.RH.models.response.RequestStatusResponse;
import com.giantLink.RH.services.RequestStatusService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RequestStatusServiceImpl implements RequestStatusService
{
    @Override
    public RequestStatusResponse add(RequestStatusRequest request)
    {
        return null;
    }

    @Override
    public List<RequestStatusResponse> get()
    {
        return null;
    }

    @Override
    public RequestStatusResponse update(RequestStatusRequest request, Long aLong)
    {
        return null;
    }

    @Override
    public void delete(Long aLong)
    {

    }

    @Override
    public RequestStatusResponse get(Long aLong)
    {
        return null;
    }
}
