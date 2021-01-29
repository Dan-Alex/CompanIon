package com.alexdan.docflow.controllers;

import com.alexdan.docflow.data.TaskRepository;
import com.alexdan.docflow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/")
    public String index(Model model, @AuthenticationPrincipal User profile) {
        if (profile != null) {
            model.addAttribute("profile", profile);
        }
        return "index";
    }

}
