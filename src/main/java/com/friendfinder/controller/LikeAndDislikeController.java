package com.friendfinder.controller;

import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.LikeAndDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LikeAndDislikeController {

    private final LikeAndDislikeService likeAndDislikeService;


//    @GetMapping("/likes/{postId}")
//    public String getLikesCount(ModelMap modelMap, @PathVariable("postId") int postId) {
//        long countLike = likeAndDislikeService.countLike(postId);
//        modelMap.addAttribute("countLike", countLike);
//        return "redirect:/posts";
//    }
//
//    @GetMapping("/dislikes/{postId}")
//    public String getDislikesCount(ModelMap modelMap, @PathVariable("postId") int postId) {
//        long countDislike = likeAndDislikeService.countDislike(postId);
//        modelMap.addAttribute("countDislike",countDislike);
//        return "redirect:/posts";
//    }

    @PostMapping("/likes/{postId}")
    public String addLike(@ModelAttribute PostLike postLike,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @PathVariable("postId") Post post) {
        likeAndDislikeService.saveLike(postLike, currentUser, post);
        return "redirect:/posts";
    }

    @PostMapping("/dislikes/{postId}")
    public String addDislike(@ModelAttribute PostLike postLike,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable("postId") Post post) {
        likeAndDislikeService.saveDislike(postLike, currentUser, post);
        return "redirect:/posts";
    }
}
