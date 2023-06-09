package com.friendfinder.service;

import com.friendfinder.dto.postDto.PostRequestDto;
import com.friendfinder.entity.Post;
import com.friendfinder.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    Page<Post> postFindPage(int pageNumber, CurrentUser currentUser);
    Page<Post> postFindPageVideo(int pageNumber, CurrentUser currentUser);
    Page<Post> postFindPageImage(int pageNumber, CurrentUser currentUser);

    Page<Post> postPageByUserId(int userId, int pageNumber);

    void postSave(PostRequestDto post, CurrentUser currentUser, MultipartFile image, MultipartFile video);

    List<Post> postUserById(int id);

    void deletePostId(int id);

    List<Post> findAll();
}
