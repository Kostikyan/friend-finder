package com.friendfinder.controller;


import com.friendfinder.entity.FriendRequest;
import com.friendfinder.entity.User;
import com.friendfinder.entity.types.FriendStatus;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/sendRequest")
    public String sendRequest(@RequestParam("sender") User sender,
                              @RequestParam("receiver") User receiver) {
        friendRequestService.save(FriendRequest.builder()
                .sender(sender)
                .receiver(receiver)
                .status(FriendStatus.PENDING)
                .build());
        return "redirect:/users/friend/profile/" + receiver.getId();
    }
}
