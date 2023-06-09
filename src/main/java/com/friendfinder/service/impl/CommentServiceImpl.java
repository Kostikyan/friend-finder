package com.friendfinder.service.impl;

import com.friendfinder.entity.Comment;
import com.friendfinder.entity.Post;
import com.friendfinder.repository.CommentRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> commentList() {
        return commentRepository.findAll();
    }

    @Override
    public void addComment(Comment comment, CurrentUser currentUser, Post post) {
        commentRepository.save(Comment.builder()
                .user(currentUser.getUser())
                .post(post)
                .commentaryText(comment.getCommentaryText())
                .datetime(LocalDateTime.now())
                .build());
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
