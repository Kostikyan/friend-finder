package com.friendfinder.service;

import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.security.CurrentUser;

import java.util.List;

public interface LikeAndDislikeService {
    void saveLike(PostLike postLike, CurrentUser currentUser, Post post);

    void saveDislike(PostLike postLike, CurrentUser currentUser, Post post);

}
