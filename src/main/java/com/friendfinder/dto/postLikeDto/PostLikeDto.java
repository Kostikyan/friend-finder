package com.friendfinder.dto.postLikeDto;

import com.friendfinder.entity.Post;
import com.friendfinder.entity.User;
import com.friendfinder.entity.types.LikeStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeDto {

    private int id;
    private LikeStatus likeStatus;
    private Post post;
    private User user;
}
