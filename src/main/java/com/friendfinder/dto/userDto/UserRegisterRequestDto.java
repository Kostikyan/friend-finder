package com.friendfinder.dto.userDto;

import com.friendfinder.entity.Country;
import com.friendfinder.entity.types.UserGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterRequestDto {
    private String name;
    private String surname;
    private String email;
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private UserGender gender;

    private String city;

    private Country country;
}
