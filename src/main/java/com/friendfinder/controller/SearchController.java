package com.friendfinder.controller;

import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import com.friendfinder.service.SearchService;
import com.friendfinder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    private final UserService userService;
    private final FriendRequestService friendRequestService;


    @PostMapping("search/friend")
    public String searchFriend(@RequestParam String keyword,
                               @AuthenticationPrincipal CurrentUser currentUser,
                               ModelMap modelMap) {
        modelMap.addAttribute("requestSenders", friendRequestService.findSenderByReceiverId(currentUser.getUser().getId()));
        modelMap.addAttribute("user", currentUser.getUser());
        modelMap.addAttribute("users",userService.userForAddFriend(currentUser));
        List<User> resultOfSearch = searchService.searchByKeyword(keyword);
        resultOfSearch.remove(currentUser.getUser());
        modelMap.addAttribute("result", resultOfSearch);
        return "resultOfSearchUsers";
    }
}
