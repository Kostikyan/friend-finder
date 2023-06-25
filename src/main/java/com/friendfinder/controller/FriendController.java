package com.friendfinder.controller;

import com.friendfinder.entity.FriendRequest;
import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.FriendRequestService;
import com.friendfinder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FriendController {

    private final FriendRequestService friendRequestService;
    private final UserService userService;

    @GetMapping("/friends")
    public String friendsPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("friends", friendRequestService.findFriendsByUserId(currentUser.getUser().getId()));
        modelMap.addAttribute("users", userService.userForAddFriend(currentUser));
        modelMap.addAttribute("requestSenders", friendRequestService.findSenderByReceiverId(currentUser.getUser().getId()));
        modelMap.addAttribute("user", currentUser.getUser());
        return "newsfeed-friends";
    }

    @GetMapping("/delete")
    public String deleteFromFriends(@RequestParam("sender") User sender,
                                    @RequestParam("receiver") User receiver) {
        FriendRequest bySenderIdAndReceiverId = friendRequestService.findBySenderIdAndReceiverId(sender.getId(), receiver.getId());
        FriendRequest byReceiverIdAndSenderId = friendRequestService.findBySenderIdAndReceiverId(receiver.getId(), sender.getId());
        if (bySenderIdAndReceiverId != null) {
            friendRequestService.delete(bySenderIdAndReceiverId);
        }
        if (byReceiverIdAndSenderId != null) {
            friendRequestService.delete(byReceiverIdAndSenderId);
        }
        return "redirect:/friends";
    }
}