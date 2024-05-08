package com.myy803.social_bookstore.controllers;

import com.myy803.social_bookstore.domain.formsdata.LoginFormData;
import com.myy803.social_bookstore.domain.formsdata.RegisterFormData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("loginFormData", new LoginFormData());
        return "auth/login";
    }

    @PostMapping("/login")
    String login() {
        return "auth/login";
    }
}
