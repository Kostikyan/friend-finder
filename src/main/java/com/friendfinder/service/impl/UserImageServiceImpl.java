package com.friendfinder.service.impl;


import com.friendfinder.entity.UserImage;
import com.friendfinder.repository.UserImageRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService {

    private final UserImageRepository userImageRepository;


    @Override
    public List<UserImage> getAllUserImage(UserImage userImage, CurrentUser currentUser) {
        List<UserImage> userImageByUserId = userImageRepository.findUserImageAllByUserId(currentUser.getUser().getId());
        if (!userImageByUserId.isEmpty()) {
            return userImageByUserId;
        }
        return null;
    }

    @Override
    public void userImageSave(UserImage userImage, CurrentUser currentUser) {
        userImageRepository.save(UserImage.builder()
                .user(currentUser.getUser())
                .imageName(currentUser.getUser().getProfilePicture())
                .build());
    }

    @Override
    public void deleteUserImageById(int id) {
        userImageRepository.deleteById(id);
    }

}
