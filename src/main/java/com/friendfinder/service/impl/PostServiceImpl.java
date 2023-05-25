package com.friendfinder.service.impl;

import com.friendfinder.entity.Post;
import com.friendfinder.repository.PostRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.PostService;
import com.friendfinder.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Value("${post.upload.image.path}")
    private String postImageUploadPath;

    @Value("${post.video.upload.image.path}")
    private String postVideoUploadPath;

    @Override
    public List<Post> postFindAll() {
        return postRepository.findAll();
    }


    @Override
    public void postSave(Post post, CurrentUser currentUser, MultipartFile multipartFile) {
        String image = ImageUtil.uploadImage(multipartFile, postImageUploadPath);
        String video = ImageUtil.uploadImage(multipartFile, postVideoUploadPath);
        post.setImgName(image);
        post.setMusicFileName(video);
        post.setUser(currentUser.getUser());
        postRepository.save(post);
    }


    @Override
    public void deletePostId(int id, CurrentUser currentUser) {
        if (currentUser.getUser().getId() == id) {
            postRepository.deleteById(id);
        }
    }
}
