package com.friendfinder.controller;

import com.friendfinder.entity.Language;
import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/language")
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping
    public String addLanguagePage(@AuthenticationPrincipal CurrentUser currentUser,
                                  ModelMap map
    ) {
        map.addAttribute("user", currentUser.getUser());
        return "edit-profile-language";
    }

    @PostMapping
    public String addLanguage(@RequestParam("language") String lang,
                              @AuthenticationPrincipal CurrentUser current
    ) {
        Language language = Language.builder()
                .language(lang)
                .user(current.getUser())
                .build();
        languageService.save(language);
        return "redirect:/language";
    }
}
