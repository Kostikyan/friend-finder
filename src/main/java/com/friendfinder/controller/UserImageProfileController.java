package com.friendfinder.controller;

import com.friendfinder.entity.User;
import com.friendfinder.entity.UserImage;
import com.friendfinder.service.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/image/profile/page")
public class UserImageProfileController {


    private final UserImageService userImageService;
    @GetMapping("/{userId}")
    public String getUserId(@PathVariable("userId") User user, ModelMap modelMap) {
        List<UserImage> userImageById = userImageService.getUserImageById(user.getId());
        modelMap.addAttribute("userPage", userImageById);
        modelMap.addAttribute("user", user);
        return "userProfileAlbum";
    }

}
