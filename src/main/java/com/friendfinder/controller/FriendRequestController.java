package com.friendfinder.controller;

import com.friendfinder.entity.FriendRequest;
import com.friendfinder.entity.User;
import com.friendfinder.entity.types.FriendStatus;
import com.friendfinder.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FriendRequestController {
    private final FriendRequestService friendRequestService;

    @GetMapping("/sendRequest")
    public String sendRequest(@RequestParam("sender") User sender,
                              @RequestParam("receiver") User receiver) {
        friendRequestService.save(FriendRequest.builder()
                .sender(sender)
                .receiver(receiver)
                .status(FriendStatus.PENDING)
                .build());
        return "redirect:/posts";
    }

    @GetMapping("accessRequest")
    public String accessRequest(@RequestParam("sender") User sender,
                                @RequestParam("receiver") User receiver) {
        FriendRequest bySenderIdAndReceiverId = friendRequestService.findBySenderIdAndReceiverId(sender.getId(), receiver.getId());
        bySenderIdAndReceiverId.setStatus(FriendStatus.ACCEPTED);
        friendRequestService.save(bySenderIdAndReceiverId);
        return "redirect:/posts";
    }
    @GetMapping("rejectRequest")
    public String rejectRequest(@RequestParam("sender") User sender,
                                @RequestParam("receiver") User receiver) {
        FriendRequest bySenderIdAndReceiverId = friendRequestService.findBySenderIdAndReceiverId(sender.getId(), receiver.getId());
        friendRequestService.delete(bySenderIdAndReceiverId);
        return "redirect:/posts";
    }
}