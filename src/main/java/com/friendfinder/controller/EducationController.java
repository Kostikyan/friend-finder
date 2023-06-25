package com.friendfinder.controller;

import com.friendfinder.entity.Education;
import com.friendfinder.entity.Post;
import com.friendfinder.entity.User;
import com.friendfinder.entity.WorkExperiences;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.EducationService;
import com.friendfinder.service.PostService;
import com.friendfinder.service.WorkExperiencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile/work-education")
public class EducationController {

    public final EducationService educationService;
    public final WorkExperiencesService workExperiencesService;

    @GetMapping()
    public String educationPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        modelMap.addAttribute("user", currentUser.getUser());
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
