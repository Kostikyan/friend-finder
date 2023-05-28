package com.friendfinder.controller;

import com.friendfinder.entity.User;
import com.friendfinder.repository.CountryRepository;
import com.friendfinder.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timeline")
public class TimelineController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/edit-basic")
    public String editBasicPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap){
        User user = currentUser.getUser();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("countries", countryRepository.findAll());
        return "edit-profile-basic";
    }

    @PostMapping("/edit-basic")
    public String editBasic(){
        // TODO: update user profile
        return "redirect:/timeline/edit-basic";
    }
}
