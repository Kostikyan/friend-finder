package com.friendfinder.service.impl;


import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.repository.PostLikeRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.LikeAndDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeAndDislikeServiceImpl implements LikeAndDislikeService {

    private final PostLikeRepository postLikeRepository;


    @Override
    public void saveLike(PostLike postLike, CurrentUser currentUser, Post post) {
        Optional<PostLike> byUserIdAndPostId = postLikeRepository.findByUserIdAndPostId(currentUser.getUser().getId(), post.getId());
        if (byUserIdAndPostId.isEmpty()) {
            postLike.setUser(currentUser.getUser());
            postLike.setPost(post);
            postLike.setLikeStatus(LikeStatus.LIKE);
            postLikeRepository.save(postLike);
        } else {
            postLikeRepository.deleteById(byUserIdAndPostId.get().getId());
        }
    }


    @Override
    public void saveDislike(PostLike postLike, CurrentUser currentUser, Post post) {
        Optional<PostLike> byUserIdAndPostId = postLikeRepository.findByUserIdAndPostId(currentUser.getUser().getId(), post.getId());
        if (byUserIdAndPostId.isEmpty()) {
            postLike.setUser(currentUser.getUser());
            postLike.setPost(post);
            postLike.setLikeStatus(LikeStatus.DISLIKE);
            postLikeRepository.save(postLike);
        } else {
            postLikeRepository.deleteById(byUserIdAndPostId.get().getId());
        }
    }
}


