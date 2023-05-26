package com.friendfinder.controller;

import com.friendfinder.entity.Post;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;


    @GetMapping
    public String postPage(ModelMap modelMap) {
        List<Post> posts = postService.postFindAll();
        modelMap.addAttribute("posts", posts);
        return "timeline";
    }

    @GetMapping("/add")
    public String postAddPage() {
        return "timeline";
    }

    @PostMapping("/add")
    public String postAdd(@ModelAttribute Post post,
                          @AuthenticationPrincipal CurrentUser currentUser) {
        postService.postSave(post, currentUser);
        return "redirect:/posts/add";
    }


    @GetMapping("/delete")
    public String deletePostById(@RequestParam("id") int id,
                                 @AuthenticationPrincipal CurrentUser currentUser) {
        postService.deletePostId(id, currentUser);
        return "redirect:/posts";

    }
}
