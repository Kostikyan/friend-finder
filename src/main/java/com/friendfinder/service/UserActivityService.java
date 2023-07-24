package com.friendfinder.service;

import com.friendfinder.entity.User;
import com.friendfinder.entity.UserActivity;

import java.util.List;

public interface UserActivityService {
    List<UserActivity> getAllByUserId(int userId);

    void save(User user, String type);
}
