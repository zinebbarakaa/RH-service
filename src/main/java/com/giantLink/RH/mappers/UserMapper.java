package com.giantLink.RH.mappers;

import com.giantLink.RH.entities.User;
import com.giantLink.RH.models.request.RegisterRequest;
import com.giantLink.RH.models.response.UserResponse;
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
public interface UserMapper  extends ApplicationMapper<RegisterRequest, UserResponse, User>{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
