package com.friendfinder.controller;

import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.InterestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/interests")
public class InterestController {

    private final InterestsService interestsService;

    @GetMapping
    public String interestPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        modelMap.addAttribute("user", currentUser.getUser());
        modelMap.addAttribute("interests", interestsService.findAllByUserId(currentUser.getUser().getId()));
        return "edit-profile-interests";
    }

    @PostMapping("/add")
    public String interestsAdd(@RequestParam("interest") String interest,
                               @AuthenticationPrincipal CurrentUser currentUser) {
        interestsService.interestSave(interest, currentUser);
        return "redirect:/interests";
    }
}
