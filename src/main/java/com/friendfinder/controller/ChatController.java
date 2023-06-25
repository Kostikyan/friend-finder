package com.friendfinder.controller;

import com.friendfinder.entity.Chat;
import com.friendfinder.entity.Message;
import com.friendfinder.entity.User;
import com.friendfinder.repository.MessageRepository;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.impl.ChatServiceImpl;
import com.friendfinder.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/newsfeed", method = RequestMethod.GET)
@RequiredArgsConstructor
public class ChatController {

    private final UserServiceImpl userService;
    private final ChatServiceImpl chatService;
    private final MessageRepository messageRepository;

    @GetMapping("/messages")
    public String messagesPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap){
        modelMap.addAttribute("user", currentUser.getUser());
        modelMap.addAttribute("chats", chatService.findAllByCurrentUserId(currentUser.getUser().getId()));
        modelMap.addAttribute("users", userService.userFindAll());
        modelMap.addAttribute("allExceptCurrentUser", userService.findAllExceptCurrentUser(currentUser.getUser().getId()));
        return "newsfeed-messages";
    }

    @PostMapping("/chat/create/{id}")
    public String createNewChat(@PathVariable("id") int userId, @AuthenticationPrincipal CurrentUser currentUser){
        if(userId == currentUser.getUser().getId()){
            return "redirect:/newsfeed/messages";
        }

        Optional<User> userById = userService.findUserById(userId);
        if (userById.isEmpty()) {
            return "redirect:/newsfeed/messages";
        }

        Optional<Chat> byCurrentUserIdAndAnotherUserId = chatService.findByCurrentUserIdAndAnotherUserId(currentUser.getUser().getId(), userId);
        if (byCurrentUserIdAndAnotherUserId.isPresent()) {
            return "redirect:/newsfeed/messages";
        }

        Chat newChat = Chat.builder()
                .anotherUser(userById.get())
                .currentUser(currentUser.getUser())
                .build();

        chatService.save(newChat);
        return "redirect:/newsfeed/messages";
    }

    @PostMapping("/send-message")
    public String sendMessage(@AuthenticationPrincipal CurrentUser currentUser,
                              @RequestParam("chatId") int id,
                              @RequestParam("content") String content){
        Optional<User> userById = userService.findUserById(id);
        if (userById.isEmpty()) {
            return "redirect:/newsfeed/messages";
        }


        messageRepository.save(Message.builder()
                .receiver(userById.get())
                .sender(currentUser.getUser())
                .content(content)
                .sentAt(LocalDateTime.now())
                .build());

        return "redirect:/newsfeed/messages";
    }
}
