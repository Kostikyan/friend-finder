package com.friendfinder.controller;

import com.friendfinder.dto.postDto.PostRequestDto;
import com.friendfinder.entity.Comment;
import com.friendfinder.entity.Post;
import com.friendfinder.entity.PostLike;
import com.friendfinder.entity.User;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final LikeAndDislikeService likeAndDislikeService;
    private final UserService userService;
    private final FriendRequestService friendRequestService;
    private final CommentService commentService;


    @GetMapping()
    public String postAddPage(ModelMap modelMap,
                              @AuthenticationPrincipal CurrentUser currentUser,
                              @ModelAttribute Post post) {
        List<Comment> comments = commentService.commentList();
        modelMap.addAttribute("comments", comments);
        return listByPage(modelMap, 1, currentUser);

    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(ModelMap modelMap, @PathVariable("pageNumber") int currentPage,
                             @AuthenticationPrincipal CurrentUser currentUser) {
        Page<Post> page = postService.postFindPage(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Post> content = page.getContent();
        List<User> requestSenders = friendRequestService.findSenderByReceiverId(currentUser.getUser().getId());

        modelMap.addAttribute("currentPage", currentPage);
        modelMap.addAttribute("totalItems", totalItems);
        modelMap.addAttribute("totalPages", totalPages);
        modelMap.addAttribute("posts", content);
        modelMap.addAttribute("user", currentUser.getUser());
        modelMap.addAttribute("users", userService.userForAddFriend(currentUser));
        modelMap.addAttribute("requestSenders", requestSenders);
        modelMap.addAttribute("allExceptCurrentUser", userService.findAllExceptCurrentUser(currentUser.getUser().getId()));
        return "newsfeed";
    }

    @PostMapping("/add")
    public String postAdd(@ModelAttribute PostRequestDto post,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @RequestParam("image") MultipartFile image,
                          @RequestParam("video") MultipartFile video) {
         postService.postSave(post, currentUser, image, video);
        return "redirect:/posts";
    }

    @PostMapping("/reaction/like/{postId}")
    public String addLike(@ModelAttribute PostLike postLike,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @PathVariable(name = "postId") Post post) {
        postLike.setLikeStatus(LikeStatus.LIKE);
        likeAndDislikeService.saveReaction(postLike, currentUser, post);
        return "redirect:/posts";
    }

    @PostMapping("/reaction/dislike/{postId}")
    public String addDislike(@ModelAttribute PostLike postLike,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable(name = "postId") Post post) {
        postLike.setLikeStatus(LikeStatus.DISLIKE);
        likeAndDislikeService.saveReaction(postLike, currentUser, post);
        return "redirect:/posts";
    }


    @PostMapping("/comment/{postId}")
    public String addComment(@ModelAttribute Comment comment,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable("postId") Post post) {
        commentService.addComment(comment, currentUser, post);
        return "redirect:/posts";
    }

    @GetMapping("/comment/delete")
    public String removeComment(@RequestParam("id") int id) {
        commentService.deleteComment(id);
        return "redirect:/posts";
    }
}
