package com.friendfinder.controller;

import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import com.friendfinder.service.SearchService;
import com.friendfinder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final UserService userService;
    private final FriendRequestService friendRequestService;

    @PostMapping("/search/friend")
    public String searchFriend(@RequestParam String keyword,
                               @AuthenticationPrincipal CurrentUser currentUser,
                               ModelMap modelMap) {
        modelMap.addAttribute("requestSenders", friendRequestService.findSenderByReceiverId(currentUser.getUser().getId()));
        modelMap.addAttribute("user", currentUser.getUser());
        modelMap.addAttribute("users",userService.userForAddFriend(currentUser));
        modelMap.addAttribute("result", searchService.searchByKeyword(keyword,currentUser));
        modelMap.addAttribute("allExceptCurrentUser", userService.findAllExceptCurrentUser(currentUser.getUser().getId()));
        return "resultOfSearchUsers";
    }
}
