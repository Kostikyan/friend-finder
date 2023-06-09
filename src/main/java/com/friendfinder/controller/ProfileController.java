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
public class ProfileController {

    private final PostService postService;
    private final LikeAndDislikeService likeAndDislikeService;
    private final CommentService commentService;

    @GetMapping
    public String postPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Post> users = postService.postUserById(currentUser.getUser().getId());
        User user = currentUser.getUser();
        List<Comment> comments = commentService.commentList();
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("comments", comments);
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

    @PostMapping("/reaction/like/{postId}")
    public String addLike(@ModelAttribute PostLike postLike,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @PathVariable(name = "postId") Post post) {
        postLike.setLikeStatus(LikeStatus.LIKE);
        likeAndDislikeService.saveReaction(postLike, currentUser, post);
        return "redirect:/users/profile";
    }
    @PostMapping("/reaction/dislike/{postId}")
    public String addDislike(@ModelAttribute PostLike postLike,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable(name = "postId") Post post) {
        postLike.setLikeStatus(LikeStatus.DISLIKE);
        likeAndDislikeService.saveReaction(postLike, currentUser, post);
        return "redirect:/users/profile";
    }

    @PostMapping("/comment/{postId}")
    public String addComment(@ModelAttribute Comment comment,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable("postId") Post post) {
        commentService.addComment(comment, currentUser, post);
        return "redirect:/users/profile";
    }

    @GetMapping("/comment/delete")
    public String removeComment(@RequestParam("id") int id) {
        commentService.deleteComment(id);
        return "redirect:/users/profile";
    }

}
