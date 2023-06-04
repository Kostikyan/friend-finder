package com.friendfinder.controller;


import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.LikeAndDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class LikeController {

    private final LikeAndDislikeService likeAndDislikeService;

    @PostMapping("/likes/{postId}")
    public String addLike(@ModelAttribute PostLike postLike,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @PathVariable("postId") Post post) {
        likeAndDislikeService.saveLike(postLike, currentUser, post);
        return "redirect:/posts/add";
    }

    @PostMapping("/dislikes/{postId}")
    public String addDislike(@ModelAttribute PostLike postLike,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable("postId") Post post) {
        likeAndDislikeService.saveDislike(postLike, currentUser, post);
        return "redirect:/posts/add";
    }


}
