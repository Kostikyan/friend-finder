package com.friendfinder.controller;

import com.friendfinder.entity.Country;
import com.friendfinder.entity.User;
import com.friendfinder.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/register")
    public String registerPage(ModelMap modelMap) {
        List<Country> allCountries = userService.findAllCountries();
        modelMap.addAttribute("countries", allCountries);
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.userSave(user);
        return "redirect:/";
    }
}
