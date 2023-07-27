package com.giantLink.RH.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;


public interface ApplicationMapper<RQ,RS,ET>
{
    void updateEntityFromRequest(RQ entityRequest, @MappingTarget ET entity);
	ET requestToEntity(RQ entityRequest);
    RS entityToResponse(ET entity);

    List<RS> listToResponseList(List<ET> entities);
}