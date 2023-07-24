package com.friendfinder.service;

import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.function.ServerResponse;

public interface SearchService {
    Page<User> searchByKeyword(String keyword, CurrentUser currentUser, int currentPage);

}
