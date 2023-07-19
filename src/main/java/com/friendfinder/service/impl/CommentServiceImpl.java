package com.friendfinder.service.impl;

import com.friendfinder.dto.commentDto.CommentRequestDto;
import com.friendfinder.entity.Comment;
import com.friendfinder.entity.Post;
import com.friendfinder.mapper.CommentMapper;
import com.friendfinder.repository.CommentRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.CommentService;
import com.friendfinder.service.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserActivityService userActivityService;
    private final CommentMapper commentMapper;

    @Override
    public List<Comment> commentList() {
        return commentRepository.findAll();
    }

    @Override
    public void addComment(CommentRequestDto comment, CurrentUser currentUser, Post post) {
        Comment commentSave = commentMapper.map(CommentRequestDto.builder()
                .user(currentUser.getUser())
                .post(post)
                .commentaryText(comment.getCommentaryText())
                .datetime(LocalDateTime.now())
                .build());
        commentRepository.save(commentSave);
        userActivityService.save(currentUser.getUser(),"commented on a post");
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
