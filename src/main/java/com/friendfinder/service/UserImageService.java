package com.friendfinder.service;

import com.friendfinder.entity.UserImage;
import com.friendfinder.security.CurrentUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserImageService {

    Page<UserImage> userImagePageByUserId(int userId, int pageNumber);

    List<UserImage> getUserImageById(int userId);

    void userImageSave(UserImage userImage, CurrentUser currentUser);


    void deleteUserImageById(int id);
}
