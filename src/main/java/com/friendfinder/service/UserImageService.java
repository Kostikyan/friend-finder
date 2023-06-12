package com.friendfinder.service;

import com.friendfinder.entity.UserImage;
import com.friendfinder.security.CurrentUser;

import java.util.List;

public interface UserImageService {
    List<UserImage> getAllUserImage(UserImage userImage, CurrentUser currentUser);

    void userImageSave(UserImage userImage, CurrentUser currentUser);

    void deleteUserImageById(int id);
}
