package com.friendfinder.service.impl;


import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.repository.PostLikeRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.LikeAndDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeAndDislikeServiceImpl implements LikeAndDislikeService {

    private final PostLikeRepository postLikeRepository;


    @Override
    public void saveLike(PostLike postLike, CurrentUser currentUser, Post post) {
        postLike.setUser(currentUser.getUser());
        postLike.setPostLikes(post);
        postLike.setLikeStatus(LikeStatus.LIKE);
        postLikeRepository.save(postLike);
    }

    @Override
    public void saveDislike(PostLike postLike, CurrentUser currentUser, Post post) {
        postLike.setUser(currentUser.getUser());
        postLike.setPostLikes(post);
        postLike.setLikeStatus(LikeStatus.DISLIKE);
        postLikeRepository.save(postLike);
    }
}
