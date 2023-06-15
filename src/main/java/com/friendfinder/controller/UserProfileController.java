package com.friendfinder.controller;

import com.friendfinder.entity.Comment;
import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.entity.User;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.CommentService;
import com.friendfinder.service.LikeAndDislikeService;
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
public class UserProfileController {

    private final PostService postService;
    private final CommentService commentService;
    private final LikeAndDislikeService likeAndDislikeService;

    @GetMapping("/{userId}")
    public String getUserId(@PathVariable("userId") User user, ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Post> attributeValue = postService.postUserById(user.getId());
        List<Comment> comments = commentService.commentList();
        modelMap.addAttribute("userPage", attributeValue);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("profile", currentUser.getUser());
        modelMap.addAttribute("comments", comments);
        return "timeline";
    }

    @PostMapping("/add")
    public String postAdd(@ModelAttribute Post post,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @RequestParam("image") MultipartFile image,
                          @RequestParam("video") MultipartFile video) {
        postService.postSave(post, currentUser, image, video);
        return "redirect:/users/profile/" + currentUser.getUser().getId();
    }

    @PostMapping("/reaction/like/{postId}")
    public String addLike(@ModelAttribute PostLike postLike,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @PathVariable(name = "postId") Post post) {
        postLike.setLikeStatus(LikeStatus.LIKE);
        likeAndDislikeService.saveReaction(postLike, currentUser, post);
        return "redirect:/users/profile/page/" + post.getUser().getId();
    }

    @PostMapping("/reaction/dislike/{postId}")
    public String addDislike(@ModelAttribute PostLike postLike,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable(name = "postId") Post post) {
        postLike.setLikeStatus(LikeStatus.DISLIKE);
        likeAndDislikeService.saveReaction(postLike, currentUser, post);
        return "redirect:/users/profile/page/" + post.getUser().getId();
    }


    @PostMapping("/comment/{postId}")
    public String addComment(@ModelAttribute Comment comment,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable("postId") Post post) {
        commentService.addComment(comment, currentUser, post);
        return "redirect:/users/profile/page/" + post.getUser().getId();
    }
}
