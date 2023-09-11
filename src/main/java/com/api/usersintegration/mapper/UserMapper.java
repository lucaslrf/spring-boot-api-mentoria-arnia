package com.api.usersintegration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import com.api.usersintegration.dto.UserRequestDTO;
import com.api.usersintegration.model.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {    
    User toUser(UserRequestDTO userRequestDTO);
}
