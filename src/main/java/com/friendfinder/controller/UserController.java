package com.friendfinder.controller;

import com.friendfinder.entity.User;
import com.friendfinder.repository.UserRepository;
import com.friendfinder.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    @GetMapping("/register")
    public String registerPage() {
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.userSave(user);
        return "redirect:/";
    }
}
