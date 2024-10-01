package com.c_s_boot.mapper;

import com.c_s_boot.DTO.SignupRequest;
import com.c_s_boot.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User signupRequestToUser(SignupRequest signupRequest);

    SignupRequest userToSignupRequest(User user);

}

