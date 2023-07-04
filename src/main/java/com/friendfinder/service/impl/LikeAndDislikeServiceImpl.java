package com.friendfinder.service.impl;


import com.friendfinder.dto.postLikeDto.PostLikeDto;
import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.mapper.PostLikeMapper;
import com.friendfinder.repository.PostLikeRepository;
import com.friendfinder.repository.PostRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.LikeAndDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeAndDislikeServiceImpl implements LikeAndDislikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final PostLikeMapper postLikeMapper;

    @Override
    @Transactional
    public void saveReaction(PostLikeDto postLikeDto, CurrentUser currentUser, Post post) {
        Optional<PostLike> byUserIdAndPostId = postLikeRepository.findByUserIdAndPostId(currentUser.getUser().getId(), post.getId());
        if (byUserIdAndPostId.isEmpty()) {
            postLikeDto.setUser(currentUser.getUser());
            postLikeDto.setPost(post);
            if (postLikeDto.getLikeStatus() == LikeStatus.LIKE) {
                post.setLikeCount(post.getLikeCount() + 1);
                postRepository.save(post);
            } else {
                post.setDislikeCount(post.getDislikeCount() + 1);
                postRepository.save(post);
            }
            PostLike postLike = postLikeMapper.map(postLikeDto);
            postLikeRepository.save(postLike);
        } else {
            PostLike postLikeDelete = byUserIdAndPostId.get();
            postLikeRepository.delete(postLikeDelete);
            if (postLikeDelete.getLikeStatus() == LikeStatus.LIKE) {
                post.setLikeCount(post.getLikeCount() - 1);
                postRepository.save(post);
            } else {
                post.setDislikeCount(post.getDislikeCount() - 1);
                postRepository.save(post);
            }
        }
    }
}


