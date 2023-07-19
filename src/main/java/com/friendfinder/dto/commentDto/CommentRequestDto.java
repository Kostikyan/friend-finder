package com.friendfinder.dto.commentDto;

import com.friendfinder.entity.Post;
import com.friendfinder.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {


    private User user;
    private Post post;
    private LocalDateTime datetime;
    private String commentaryText;
}
