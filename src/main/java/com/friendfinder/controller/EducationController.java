package com.friendfinder.controller;

import com.friendfinder.entity.Education;
import com.friendfinder.entity.WorkExperiences;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.EducationService;
import com.friendfinder.service.WorkExperiencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile/work-education")
public class EducationController {

    public final EducationService educationService;
    public final WorkExperiencesService workExperiencesService;

    @GetMapping
    public String educationPage() {
        return "edit-profile-work-edu";
    }

    @PostMapping("/education/add")
    public String educationAdd(@ModelAttribute Education education,
                               @AuthenticationPrincipal CurrentUser currentUser) {
        educationService.saveEducation(education, currentUser);
        return "redirect:/profile/work-education";
    }

    @PostMapping("/work/add")
    public String workAdd(@ModelAttribute WorkExperiences workExperiences,
                          @AuthenticationPrincipal CurrentUser currentUser) {
        workExperiencesService.saveWorkExperiences(workExperiences, currentUser);
        return "redirect:/profile/work-education";
    }
}
