package com.friendfinder.service;

import com.friendfinder.entity.Comment;
import com.friendfinder.entity.Post;
import com.friendfinder.security.CurrentUser;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    void addComment(Comment comment, CurrentUser currentUser, Post post);

    void deleteComment(int id);

    List<Comment> commentList();
}
