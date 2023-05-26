package com.friendfinder.controller;

import com.friendfinder.entity.User;
import com.friendfinder.service.WorkExperiencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/workExperiences")
@RequiredArgsConstructor
public class WorkExperiencesController {
    private final WorkExperiencesService workExperiencesService;

    @GetMapping("/about")
    public String workExperiences(ModelMap modelMap, @AuthenticationPrincipal User user){
        modelMap.addAttribute("workExperiences",workExperiencesService.findAllByUserId(user.getId()));
        return "timeline-about";
    }
}
