package com.friendfinder.dto.postDto;

import com.friendfinder.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PostRequestDto {


    private String description;
    private String imgName;
    private String musicFileName;
    private Date postDatetime;
    private User user;
}
