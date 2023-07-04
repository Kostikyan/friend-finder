package com.friendfinder.service;

import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;

import java.util.List;

public interface SearchService {
    List<User> searchByKeyword(String keyword, CurrentUser currentUser);
}
