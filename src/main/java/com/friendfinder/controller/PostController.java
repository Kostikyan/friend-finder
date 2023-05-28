package com.friendfinder.controller;

import com.friendfinder.entity.Post;
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
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public String postPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Post> posts = postService.postFindAll();
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("user", currentUser.getUser());
        return "newsfeed";
    }

    @GetMapping("/add")
    public String postAddPage(ModelMap modelMap,
                              @AuthenticationPrincipal CurrentUser currentUser) {
        List<Post> posts = postService.postFindAll();
        modelMap.addAttribute("posts", posts);
        modelMap.addAttribute("user", currentUser.getUser());
        return "newsfeed";
    }

    @PostMapping("/add")
    public String postAdd(@ModelAttribute Post post,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @RequestParam("image") MultipartFile image,
                          @RequestParam("video") MultipartFile video) {
        postService.postSave(post, currentUser, image, video);
        return "redirect:/posts";
    }
}
