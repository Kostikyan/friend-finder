package com.friendfinder.service.impl;


import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.repository.PostLikeRepository;
import com.friendfinder.repository.PostRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.LikeAndDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LikeAndDislikeServiceImpl implements LikeAndDislikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Override
    public void saveReaction(PostLike postLike, CurrentUser currentUser, Post post) {
        Optional<PostLike> byUserIdAndPostId = postLikeRepository.findByUserIdAndPostId(currentUser.getUser().getId(), post.getId());
        if (byUserIdAndPostId.isEmpty()) {
            postLike.setUser(currentUser.getUser());
            postLike.setPost(post);
            if (postLike.getLikeStatus() == LikeStatus.LIKE) {
                post.setLikeCount(post.getLikeCount() + 1);
                postRepository.save(post);
            }
            else {
                post.setDislikeCount(post.getDislikeCount() + 1);
                postRepository.save(post);
            }
            postLikeRepository.save(postLike);
        } else {
            PostLike postLike1 = byUserIdAndPostId.get();
            postLikeRepository.delete(postLike1);
            if(postLike1.getLikeStatus()==LikeStatus.LIKE){
                post.setLikeCount(post.getLikeCount() - 1);
                postRepository.save(post);
            }
            else {
                post.setDislikeCount(post.getDislikeCount() - 1);
                postRepository.save(post);
            }
        }
    }
}


