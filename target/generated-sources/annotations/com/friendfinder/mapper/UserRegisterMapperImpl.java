package com.friendfinder.mapper;

import com.friendfinder.dto.userDto.UserRegisterRequestDto;
import com.friendfinder.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T23:46:58+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserRegisterMapperImpl implements UserRegisterMapper {

    @Override
    public User map(UserRegisterRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( requestDto.getName() );
        user.setSurname( requestDto.getSurname() );
        user.setEmail( requestDto.getEmail() );
        user.setPassword( requestDto.getPassword() );
        user.setDateOfBirth( requestDto.getDateOfBirth() );
        user.setGender( requestDto.getGender() );
        user.setCity( requestDto.getCity() );
        user.setCountry( requestDto.getCountry() );

        return user;
    }

    @Override
    public UserRegisterRequestDto mapToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();

        userRegisterRequestDto.setName( user.getName() );
        userRegisterRequestDto.setSurname( user.getSurname() );
        userRegisterRequestDto.setEmail( user.getEmail() );
        userRegisterRequestDto.setPassword( user.getPassword() );
        userRegisterRequestDto.setDateOfBirth( user.getDateOfBirth() );
        userRegisterRequestDto.setGender( user.getGender() );
        userRegisterRequestDto.setCity( user.getCity() );
        userRegisterRequestDto.setCountry( user.getCountry() );

        return userRegisterRequestDto;
    }
}
