package com.friendfinder.service;

import com.friendfinder.dto.postLikeDto.PostLikeDto;
import com.friendfinder.entity.Post;
import com.friendfinder.security.CurrentUser;

public interface LikeAndDislikeService {
    void saveReaction(PostLikeDto postLikeDto, CurrentUser currentUser, Post post);
}
