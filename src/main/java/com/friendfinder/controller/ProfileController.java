package com.friendfinder.controller;

import com.friendfinder.entity.Post;
import com.friendfinder.entity.User;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/profile")
public class ProfileController {

    private final PostService postService;

    @GetMapping
    public String postPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Post> users = postService.postUserById(currentUser.getUser().getId());
        User user = currentUser.getUser();
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("user", user);
        return "timeline";
    }

    @PostMapping("/add")
    public String postAdd(@ModelAttribute Post post,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @RequestParam("image") MultipartFile image,
                          @RequestParam("video") MultipartFile video) {
        postService.postSave(post, currentUser, image, video);
        return "redirect:/users/profile";
    }


    @GetMapping("/delete")
    public String deletePostById(@RequestParam("id") int id,
                                 @AuthenticationPrincipal CurrentUser currentUser) {
        postService.deletePostId(id, currentUser);
        return "redirect:/users/profile";

    }
}
