package com.friendfinder.controller;

import com.friendfinder.dto.postDto.PostRequestDto;
import com.friendfinder.dto.postLikeDto.PostLikeDto;
import com.friendfinder.entity.Comment;
import com.friendfinder.entity.FriendRequest;
import com.friendfinder.entity.Post;
import com.friendfinder.entity.User;
import com.friendfinder.entity.types.FriendStatus;
import com.friendfinder.entity.types.LikeStatus;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.*;
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
    private final FriendRequestService friendRequestService;
    private final UserActivityService userActivityService;

    @GetMapping("/{userId}")
    public String getUserId(@PathVariable("userId") User user, ModelMap modelMap,
                            @AuthenticationPrincipal CurrentUser currentUser) {
        List<Post> attributeValue = postService.postUserById(user.getId());
        List<Comment> comments = commentService.commentList();
        modelMap.addAttribute("userPage", attributeValue);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("profile", currentUser.getUser());
        modelMap.addAttribute("comments", comments);
        modelMap.addAttribute("friendsCount", friendRequestService.findFriendsByUserIdCount(user.getId()));
        modelMap.addAttribute("userActivity", userActivityService.getAllByUserId(user.getId()));
        return "timeline";
    }

    @PostMapping("/add")
    public String postAdd(@ModelAttribute PostRequestDto post,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @RequestParam("image") MultipartFile image,
                          @RequestParam("video") MultipartFile video) {
        postService.postSave(post, currentUser, image, video);
        return "redirect:/users/profile/" + currentUser.getUser().getId();
    }

    @GetMapping("/send-request")
    public String sendRequest(@RequestParam("sender") User sender,
                              @RequestParam("receiver") User receiver) {
        friendRequestService.save(FriendRequest.builder()
                .sender(sender)
                .receiver(receiver)
                .status(FriendStatus.PENDING)
                .build());
        return "redirect:/users/profile/" + receiver.getId();
    }

    @GetMapping("/delete")
    public String deletePostById(@RequestParam("id") int id,
                                 @AuthenticationPrincipal CurrentUser currentUser) {
        postService.deletePostId(id);
        return "redirect:/users/profile/" + currentUser.getUser().getId();

    }

    @PostMapping("/reaction/like/{postId}")
    public String addLike(@ModelAttribute PostLikeDto postLikeDto,
                          @AuthenticationPrincipal CurrentUser currentUser,
                          @PathVariable(name = "postId") Post post) {
        postLikeDto.setLikeStatus(LikeStatus.LIKE);
        likeAndDislikeService.saveReaction(postLikeDto, currentUser, post);
        return "redirect:/users/profile/" + post.getUser().getId();
    }

    @PostMapping("/reaction/dislike/{postId}")
    public String addDislike(@ModelAttribute PostLikeDto postLikeDto,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable(name = "postId") Post post) {
        postLikeDto.setLikeStatus(LikeStatus.DISLIKE);
        likeAndDislikeService.saveReaction(postLikeDto, currentUser, post);
        return "redirect:/users/profile/" + post.getUser().getId();
    }


    @PostMapping("/comment/{postId}")
    public String addComment(@ModelAttribute Comment comment,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable("postId") Post post) {
        commentService.addComment(comment, currentUser, post);
        return "redirect:/users/profile/page/" + post.getUser().getId();
    }

    @GetMapping("/comment/delete")
    public String removeComment(@RequestParam("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        commentService.deleteComment(id);
        return "redirect:/users/profile/" +currentUser.getUser().getId();
    }
}
