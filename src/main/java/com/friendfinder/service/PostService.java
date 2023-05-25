package com.friendfinder.service;

import com.friendfinder.entity.Post;
import com.friendfinder.security.CurrentUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    List<Post> postFindAll();

    void postSave(Post post, CurrentUser currentUser, MultipartFile multipartFile);

    void deletePostId(int id, CurrentUser currentUser);
}
