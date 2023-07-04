package com.friendfinder.controller;

import com.friendfinder.entity.FriendRequest;
import com.friendfinder.entity.User;
import com.friendfinder.entity.types.FriendStatus;
import com.friendfinder.service.FriendRequestService;
import com.friendfinder.service.impl.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FriendRequestController {
    private final FriendRequestService friendRequestService;
    private final MailService mailService;

    @GetMapping("/send-request")
    public String sendRequest(@RequestParam("sender") User sender,
                              @RequestParam("receiver") User receiver) {
        friendRequestService.save(FriendRequest.builder()
                .sender(sender)
                .receiver(receiver)
                .status(FriendStatus.PENDING)
                .build());
        mailService.sendMail(receiver.getEmail(), "You have a new friend request", "Hi, " + receiver.getName() +
                ". You have an friend request from " + sender.getName() + " " + sender.getSurname());
        return "redirect:/posts";
    }

    @GetMapping("/access-request")
    public String accessRequest(@RequestParam("sender") User sender,
                                @RequestParam("receiver") User receiver) {
        FriendRequest bySenderIdAndReceiverId = friendRequestService.findBySenderIdAndReceiverId(sender.getId(), receiver.getId());
        friendRequestService.changeStatus(bySenderIdAndReceiverId);
        mailService.sendMail(sender.getEmail(), "Your friend request is accepted", "Hi, " + sender.getName() +
                ". " + receiver.getName() + " accepted your request.");
        return "redirect:/posts";
    }

    @GetMapping("/reject-request")
    public String rejectRequest(@RequestParam("sender") User sender,
                                @RequestParam("receiver") User receiver) {
        FriendRequest bySenderIdAndReceiverId = friendRequestService.findBySenderIdAndReceiverId(sender.getId(), receiver.getId());
        friendRequestService.delete(bySenderIdAndReceiverId);
        return "redirect:/posts";
    }
}