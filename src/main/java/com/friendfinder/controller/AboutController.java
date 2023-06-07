package com.friendfinder.controller;

import com.friendfinder.entity.Interest;
import com.friendfinder.entity.Language;
import com.friendfinder.entity.User;
import com.friendfinder.entity.WorkExperiences;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.InterestsService;
import com.friendfinder.service.LanguageService;
import com.friendfinder.service.UserService;
import com.friendfinder.service.WorkExperiencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class AboutController {
    private final WorkExperiencesService workExperiencesService;
    private final InterestsService interestsService;
    private final LanguageService languageService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/about")
    public String workExperiences(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            List<WorkExperiences> workExperiencesList = workExperiencesService.findAllByUserId(user.getId());
            List<Interest> interestList = interestsService.findAllByUserId(user.getId());
            List<Language> languageList = languageService.findAllByUserId(user.getId());

            modelMap.addAttribute("workExperiences", workExperiencesList);
            modelMap.addAttribute("interests", interestList);
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("languages", languageList);
        }
        return "timeline-about";
    }

    @GetMapping("/changePassword")
    public String changePasswordPage() {
        return "edit-profile-password";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("oldPass") String oldPass,
                                 @RequestParam("newPass") String newPass,
                                 @RequestParam("confPass") String confPass,
                                 @AuthenticationPrincipal CurrentUser currentUser,
                                 ModelMap modelMap) {
        User user = currentUser.getUser();
        if (passwordEncoder.matches(oldPass, user.getPassword())) {
            if (newPass.equals(confPass)) {
                String encodedPass = passwordEncoder.encode(newPass);
                user.setPassword(encodedPass);
                userService.updateUserPasswordById(encodedPass, user.getId());
                return "redirect:/posts";
            }
            modelMap.addAttribute("massage", "Password is not confirmed.");
            return "redirect:/user/changePassword";
        }
        modelMap.addAttribute("massage", "Incorrect password");
        return "redirect:/user/changePassword";
    }
}
