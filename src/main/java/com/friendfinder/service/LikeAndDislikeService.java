package com.friendfinder.service;

import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.security.CurrentUser;

public interface LikeAndDislikeService {
    void saveLike(PostLike postLike, CurrentUser currentUser, Post post);

    void saveDislike(PostLike postLike, CurrentUser currentUser, Post post);

}
