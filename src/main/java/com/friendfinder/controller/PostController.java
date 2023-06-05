package com.friendfinder.controller;

import com.friendfinder.entity.Post;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.LikeAndDislikeService;
import com.friendfinder.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final LikeAndDislikeService likeAndDislikeService;

    @GetMapping()
    public String postAddPage(ModelMap modelMap,
                              @AuthenticationPrincipal CurrentUser currentUser,
                              @ModelAttribute Post post) {
        return listByPage(modelMap, 1, currentUser);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(ModelMap modelMap, @PathVariable("pageNumber") int currentPage,
                             @AuthenticationPrincipal CurrentUser currentUser) {
        Page<Post> page = postService.postFindPage(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Post> content = page.getContent();

        modelMap.addAttribute("currentPage", currentPage);
        modelMap.addAttribute("totalItems", totalItems);
        modelMap.addAttribute("totalPages", totalPages);
        modelMap.addAttribute("posts", content);
        modelMap.addAttribute("user", currentUser.getUser());
        return "newsfeed";
    }

    @PostMapping("/add")
    public String postAdd(@ModelAttribute Post post,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @RequestParam("image") MultipartFile image,
                          @RequestParam("video") MultipartFile video) {
        postService.postSave(post, currentUser, image, video);
        return "redirect:/posts/add";
    }
}
