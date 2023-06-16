package com.friendfinder.controller;


import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/friend/profile")
public class UserFriendProfileController {

    private final FriendRequestService friendRequestService;

    @GetMapping("/{userId}")
    public String friendsPage(@PathVariable("userId") User user, ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("friends", friendRequestService.findFriendsByUserId(currentUser.getUser().getId()));
        modelMap.addAttribute("profile", currentUser.getUser());
        modelMap.addAttribute("user", user);
        return "timeline-friends";
    }
}
