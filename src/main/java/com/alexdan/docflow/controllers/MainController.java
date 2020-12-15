package com.alexdan.docflow.controllers;

import com.alexdan.docflow.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        return "profile";
    }
}
