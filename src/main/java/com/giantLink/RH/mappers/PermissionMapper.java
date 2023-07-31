package com.giantLink.RH.mappers;


import com.giantLink.RH.entities.Permission;
import com.giantLink.RH.models.request.PermissionRequest;
import com.giantLink.RH.models.response.PermissionResponse;
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
public interface PermissionMapper  extends ApplicationMapper<PermissionRequest, PermissionResponse, Permission>{

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

}
