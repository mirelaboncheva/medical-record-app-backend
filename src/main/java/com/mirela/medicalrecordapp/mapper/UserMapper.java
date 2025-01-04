package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.UserRequest;
import com.mirela.medicalrecordapp.dto.UserResponse;
import com.mirela.medicalrecordapp.dto.UserUpdateRequest;
import com.mirela.medicalrecordapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest userRequest);
    UserResponse toDto(User user);
    List<UserResponse> toDtoList(List<User> users);
    UserUpdateRequest toUserUpdateRequest(User user);
}
