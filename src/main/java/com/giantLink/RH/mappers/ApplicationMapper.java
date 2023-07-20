package com.giantLink.RH.mappers;

import java.util.List;

public interface ApplicationMapper<RQ,RS,ET> {
	ET requestToEntity(RQ entityRequest);
    RS entityToResponse(ET entity);


    List<RS> listToResponseList(List<ET> entities);
    


}