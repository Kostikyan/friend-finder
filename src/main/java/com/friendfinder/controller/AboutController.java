package com.friendfinder.controller;

import com.friendfinder.entity.Interest;
import com.friendfinder.entity.User;
import com.friendfinder.entity.WorkExperiences;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.InterestsService;
import com.friendfinder.service.WorkExperiencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class AboutController {
    private final WorkExperiencesService workExperiencesService;
    private final InterestsService interestsService;

    @GetMapping("/about")
    public String workExperiences(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            List<WorkExperiences> workExperiencesList = workExperiencesService.findAllByUserId(user.getId());
            List<Interest> interestList = interestsService.findAllByUserId(user.getId());

            modelMap.addAttribute("workExperiences", workExperiencesList);
            modelMap.addAttribute("interests", interestList);
        }
        return "timeline-about";
    }
}
