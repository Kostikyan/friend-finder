package com.friendfinder.mapper;

import com.friendfinder.dto.userDto.UserRegisterRequestDto;
import com.friendfinder.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {
    User map(UserRegisterRequestDto requestDto);
    UserRegisterRequestDto mapToDto(User user);

}
