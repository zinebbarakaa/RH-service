package com.giantLink.RH.mappers;

import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.models.request.RequestStatusRequest;
import com.giantLink.RH.models.response.RequestStatusResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RequestStatusMapper extends ApplicationMapper<RequestStatusRequest, RequestStatusResponse, RequestStatus> {
    RequestStatusMapper INSTANCE = Mappers.getMapper(RequestStatusMapper.class);
}
