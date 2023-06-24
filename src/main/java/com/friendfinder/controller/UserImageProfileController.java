package com.friendfinder.controller;

import com.friendfinder.entity.FriendRequest;
import com.friendfinder.entity.User;
import com.friendfinder.entity.UserImage;
import com.friendfinder.entity.types.FriendStatus;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import com.friendfinder.service.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/image/profile")
public class UserImageProfileController {


    private final UserImageService userImageService;
    private final FriendRequestService friendRequestService;

    @GetMapping("/{userId}")
    public String getUserId(@PathVariable("userId") User user, ModelMap modelMap,
                            @AuthenticationPrincipal CurrentUser currentUser) {
        List<UserImage> userImageById = userImageService.getUserImageById(user.getId());
        modelMap.addAttribute("userPage", userImageById);
        modelMap.addAttribute("profile", currentUser.getUser());
        modelMap.addAttribute("user", user);
        return "timeline-album";
    }

    @GetMapping("/sendRequest")
    public String sendRequest(@RequestParam("sender") User sender,
                              @RequestParam("receiver") User receiver) {
        friendRequestService.save(FriendRequest.builder()
                .sender(sender)
                .receiver(receiver)
                .status(FriendStatus.PENDING)
                .build());
        return "redirect:/users/image/profile/" + receiver.getId();
    }
}