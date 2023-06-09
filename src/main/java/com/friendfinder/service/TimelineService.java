package com.friendfinder.service;

import com.friendfinder.entity.Country;
import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TimelineService {
    List<Country> findAllCountries();

    void updateUser(@ModelAttribute User user, @AuthenticationPrincipal CurrentUser currentUser);

    void updateUserProfilePic(MultipartFile profilePic, CurrentUser currentUser);

    void updateUserProfileBackgroundPic(MultipartFile bgPic, CurrentUser currentUser);
}
