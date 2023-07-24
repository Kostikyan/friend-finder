package com.friendfinder.controller;

import com.friendfinder.security.CurrentUser;
import com.friendfinder.service.impl.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final MailService mailService;

    @GetMapping
    public String contactPage(
            @AuthenticationPrincipal CurrentUser currentUser,
            ModelMap modelMap
    ){
        modelMap.addAttribute("user", currentUser.getUser());
        return "contact-form";
    }

    @PostMapping("/send-contact-form")
    public String sendContact(
            @RequestParam("name") String name,
            @RequestParam("email") String fromEmail,
            @RequestParam("subject") String subject,
            @RequestParam("message") String text
    ){
        String toEmail = "finderfriend2023@gmail.com";
        mailService.sendFromMail(toEmail, fromEmail, subject, name ,text);

        return "redirect:/contact";
    }
}
