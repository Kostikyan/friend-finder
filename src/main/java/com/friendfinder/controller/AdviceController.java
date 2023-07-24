package com.friendfinder.controller;

import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class AdviceController {

    private final UserActivityService userActivityService;

    @ModelAttribute("currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            return currentUser.getUser();
        } else {
            return null;
        }
    }

}
