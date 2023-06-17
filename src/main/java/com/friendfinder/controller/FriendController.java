package com.friendfinder.controller;

import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FriendController {
    private final FriendRequestService friendRequestService;

    @GetMapping("/friendsPage")
    public String friendsPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser){
        modelMap.addAttribute("friends",friendRequestService.findFriendsByUserId(currentUser.getUser().getId()));
        return "newsfeed-friends";
    }
}