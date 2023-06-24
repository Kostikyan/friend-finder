package com.friendfinder.controller;

import com.friendfinder.entity.Comment;
import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.entity.User;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/posts/images")
public class PostImageController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    private final LikeAndDislikeService likeAndDislikeService;
    private final FriendRequestService friendRequestService;


    @GetMapping("/{userId}")
    public String getUserId(@PathVariable("userId") User user, ModelMap modelMap,
                            @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("userPage", postService.getAllPostFriends(currentUser));
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("profile", currentUser.getUser());
        modelMap.addAttribute("comments", commentService.commentList());
        modelMap.addAttribute("users", userService.userForAddFriend(currentUser));
        modelMap.addAttribute("requestSenders", friendRequestService.findSenderByReceiverId(currentUser.getUser().getId()));
        return "newsfeed-images";
    }

    @PostMapping("/reaction/like/{postId}")
    public String addLike(@ModelAttribute PostLike postLike,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @PathVariable(name = "postId") Post post) {
        postLike.setLikeStatus(LikeStatus.LIKE);
        likeAndDislikeService.saveReaction(postLike, currentUser, post);
        return "redirect:/posts/images/" + currentUser.getUser().getId();
    }

    @PostMapping("/reaction/dislike/{postId}")
    public String addDislike(@ModelAttribute PostLike postLike,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable(name = "postId") Post post) {
        postLike.setLikeStatus(LikeStatus.DISLIKE);
        likeAndDislikeService.saveReaction(postLike, currentUser, post);
        return "redirect:/posts/images/" + currentUser.getUser().getId();
    }

    @PostMapping("/comment/{postId}")
    public String addComment(@ModelAttribute Comment comment,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable("postId") Post post) {
        commentService.addComment(comment, currentUser, post);
        return "redirect:/posts/images/" + currentUser.getUser().getId();
    }
}
