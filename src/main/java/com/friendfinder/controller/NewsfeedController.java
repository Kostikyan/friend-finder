package com.friendfinder.controller;

import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newsfeed")
@RequiredArgsConstructor
public class NewsfeedController {

    private final UserServiceImpl userService;

    @GetMapping("/people-nearby")
    public String peopleNearbyPage(){
        return "newsfeed-people-nearby";
    }
    @GetMapping("/friends")
    public String friendsPage(){
        return "newsfeed-friends";
    }
    @GetMapping("/messages")
    public String messagesPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap){
        modelMap.addAttribute("user", currentUser.getUser());
        modelMap.addAttribute("users", userService.userFindAll());
        return "newsfeed-messages";
    }
    @GetMapping("/images")
    public String imagesPage(){
        return "newsfeed-images";
    }
    @GetMapping("/videos")
    public String videosPage(){
        return "newsfeed-videos";
    }
}
