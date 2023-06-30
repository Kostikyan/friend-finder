package com.friendfinder.controller;

import com.friendfinder.dto.userDto.UserRegisterRequestDto;
import com.friendfinder.entity.Country;
import com.friendfinder.entity.User;
import com.friendfinder.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    @Value("${site.url}")
    String siteUrl;

    @GetMapping("/register")
    public String registerPage(ModelMap modelMap) {
        List<Country> allCountries = userService.findAllCountries();
        modelMap.addAttribute("countries", allCountries);

        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterRequestDto userDto) {
        userService.userRegister(userDto);
        return "redirect:/";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam String email,
                             @RequestParam String token) {
        Optional<User> byEmail = userService.findByEmail(email);
        if (byEmail.isEmpty()) {
            return "redirect:/google.com";
        }
        if (byEmail.get().isEnabled()) {
            return "redirect:/yandex.ru";
        }
        if (byEmail.get().getToken().equals(token)) {
            User user = byEmail.get();
            user.setEnabled(true);
            user.setToken(null);
            userService.save(user);
        }
        return "redirect:/";
    }
}
