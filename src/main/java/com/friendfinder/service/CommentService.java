package com.friendfinder.service;

import com.friendfinder.dto.commentDto.CommentRequestDto;
import com.friendfinder.entity.Comment;
import com.friendfinder.entity.Post;
import com.friendfinder.security.CurrentUser;

import java.util.List;

public interface CommentService {
    void addComment(CommentRequestDto comment, CurrentUser currentUser, Post post);

    void deleteComment(int id);

    List<Comment> commentList();
}
