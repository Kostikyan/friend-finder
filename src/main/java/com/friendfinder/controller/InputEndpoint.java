package com.friendfinder.controller;

import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.impl.MainServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class InputEndpoint {

    public final MainServiceImpl mainService;

    @GetMapping("/")
    public String mainPage(ModelMap modelMap,
                           @AuthenticationPrincipal CurrentUser currentUser) {

        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
            return "newsfeed";
        }

        modelMap.addAttribute("countries", mainService.findAllCountries());
        return "index";
    }

    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("imageName") String imageName) throws IOException {
        return mainService.getImage(imageName);
    }

    @GetMapping(value = "/getProfilePic",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProfilePic(@RequestParam("imageName") String imageName) throws IOException {
        return mainService.getProfilePic(imageName);
    }

    @GetMapping(value = "/getBgProfilePic",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getBgProfilePic(@RequestParam("imageName") String imageName) throws IOException {
        return mainService.getBgProfilePic(imageName);
    }

    @GetMapping("/login-register")
    public String loginPage(ModelMap modelMap) {
        modelMap.addAttribute("countries", mainService.findAllCountries());
        return "index";
    }

    @GetMapping("/successLogin")
    public String customSuccessLogin() {
        return "redirect:/posts";
    }
}
