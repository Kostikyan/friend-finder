package com.friendfinder.service.impl;

import com.friendfinder.entity.Post;
import com.friendfinder.entity.User;
import com.friendfinder.repository.PostRepository;
import com.friendfinder.repository.UserRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import com.friendfinder.service.PostService;
import com.friendfinder.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FriendRequestService friendRequestService;

    @Value("${post.upload.image.path}")
    private String postImageUploadPath;

    @Value("${post.video.upload.image.path}")
    private String postVideoUploadPath;

    @Override
    public Page<Post> postFindPage(int pageNumber) {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return postRepository.findAll(pageable);
    }

    @Override
    public void postSave(Post post, CurrentUser currentUser, MultipartFile image, MultipartFile video) {
        postRepository.save(Post.builder()
                .imgName(ImageUtil.uploadImage(image, postImageUploadPath))
                .musicFileName(ImageUtil.uploadImage(video, postVideoUploadPath))
                .postDatetime(new Date())
                .user(currentUser.getUser())
                .description(post.getDescription())
                .build());
    }

    @Override
    public List<Post> getAllPostFriends(CurrentUser currentUser) {
        List<User> friendsByUserId = friendRequestService.findFriendsByUserId(currentUser.getUser().getId());
        List<Integer> friendsIds = friendsByUserId
                .stream()
                .map(User::getId)
                .toList();

        List<Post> postList = new ArrayList<>();
        for (Integer friendsId : friendsIds) {
            postList.addAll(postRepository.findByUserId(friendsId));
        }

        return postList;
    }


    @Override
    public List<Post> postUserById(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            return postRepository.findByUserId(user.getId());
        }
        return null;

    }

    @Override
    public void deletePostId(int id, CurrentUser currentUser) {
        if (currentUser.getUser().getId() == id) {
            postRepository.deleteById(id);
        }
    }
}
