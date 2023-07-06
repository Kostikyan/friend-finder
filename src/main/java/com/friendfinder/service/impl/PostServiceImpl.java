package com.friendfinder.service.impl;

import com.friendfinder.dto.postDto.PostRequestDto;
import com.friendfinder.dto.postDto.PostResponseDto;
import com.friendfinder.entity.Post;
import com.friendfinder.entity.User;
import com.friendfinder.mapper.PostMapper;
import com.friendfinder.repository.PostRepository;
import com.friendfinder.repository.UserRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import com.friendfinder.service.PostService;
import com.friendfinder.service.UserActivityService;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FriendRequestService friendRequestService;
    private final PostMapper postMapper;
    private final UserActivityService userActivityService;

    @Value("${post.upload.image.path}")
    private String postImageUploadPath;

    @Value("${post.video.upload.image.path}")
    private String postVideoUploadPath;

    @Override
    public Page<Post> postFindPage(int pageNumber, CurrentUser currentUser) {
        List<PostResponseDto> allPostFriends = getAllPostFriends(currentUser);
        List<Integer> friendIds = allPostFriends.stream()
                .map(post -> post.getUser().getId())
                .collect(Collectors.toList());
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);

        return postRepository.findByUserIdIn(friendIds, pageable);
    }

    @Override
    public void postSave(PostRequestDto requestDto, CurrentUser currentUser, MultipartFile image, MultipartFile video) {
        String imgName = ImageUtil.uploadImage(image, postImageUploadPath);
        String musicFileName = ImageUtil.uploadImage(video, postVideoUploadPath);
        Post post = postMapper.map(PostRequestDto.builder()
                .imgName(imgName)
                .musicFileName(musicFileName)
                .postDatetime(new Date())
                .description(requestDto.getDescription())
                .user(currentUser.getUser())
                .build());
        postRepository.save(post);
        if (imgName != null) {
            userActivityService.save(currentUser.getUser(), "posted a photo");
        } else {
            userActivityService.save(currentUser.getUser(), "posted a video");
        }

    }

    @Override
    public List<PostResponseDto> getAllPostFriends(CurrentUser currentUser) {
        List<User> friendsByUserId = friendRequestService.findFriendsByUserId(currentUser.getUser().getId());
        List<Integer> friendsIds = friendsByUserId
                .stream()
                .map(User::getId)
                .toList();

        List<PostResponseDto> postList = new ArrayList<>();
        for (Integer friendsId : friendsIds) {
            postList.addAll(postMapper.mapResp(postRepository.findByUserId(friendsId)));
        }

        return postList;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
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
    public void deletePostId(int id) {
        postRepository.deleteById(id);
    }
}
