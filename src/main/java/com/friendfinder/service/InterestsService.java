package com.friendfinder.service;

import com.friendfinder.entity.Interest;
import com.friendfinder.security.CurrentUser;

import java.util.List;

public interface InterestsService {
    List<Interest> findAllByUserId(int userId);

    void interestSave(String interest, CurrentUser currentUser);
}
