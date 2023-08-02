package com.giantLink.RH.mappers;

import com.giantLink.RH.entities.Role;
import com.giantLink.RH.models.request.RoleRequest;
import com.giantLink.RH.models.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface RoleMapper extends ApplicationMapper<RoleRequest, RoleResponse, Role> {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

}

