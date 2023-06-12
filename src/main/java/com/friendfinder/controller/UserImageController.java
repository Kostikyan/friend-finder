package com.friendfinder.controller;


import com.friendfinder.entity.UserImage;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/profile/album")
public class UserImageController {

    private final UserImageService userImageService;


    @GetMapping()
    public String getUserImage(ModelMap modelMap, @ModelAttribute UserImage userImage,
                               @AuthenticationPrincipal CurrentUser currentUser) {
        List<UserImage> userImages = userImageService.getAllUserImage(userImage, currentUser);
        modelMap.addAttribute("userImages", userImages);
        modelMap.addAttribute("user", currentUser.getUser());
        return "timeline-album";
    }

    @GetMapping("/delete")
    public String deleteImageById(@RequestParam("id") int id) {
        userImageService.deleteUserImageById(id);
        return "redirect:/users/profile/album";
    }

}
