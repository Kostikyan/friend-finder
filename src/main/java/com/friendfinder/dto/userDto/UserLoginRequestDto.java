package com.friendfinder.dto.userDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginRequestDto {
    private String username;
    private String password;
}
