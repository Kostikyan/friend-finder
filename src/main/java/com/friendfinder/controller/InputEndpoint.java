package com.friendfinder.controller;

import com.friendfinder.entity.User;
import com.friendfinder.repository.CountryRepository;
import com.friendfinder.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InputEndpoint {

    @Autowired
    private  CountryRepository countryRepository;

    @GetMapping("/")
    public String mainPage(ModelMap modelMap,
                           @AuthenticationPrincipal CurrentUser currentUser) {

        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
        }

        return "newsfeed";
    }

    @GetMapping("/login-register")
    public String loginPage(ModelMap modelMap){
        modelMap.addAttribute("countries", countryRepository.findAll());
        return "index";
    }

    @GetMapping("/successLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser){
        if (currentUser != null){
            User user = currentUser.getUser();
            return "redirect:/";
        }
        return "redirect:/";
    }
}
