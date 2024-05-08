package com.myy803.social_bookstore.controllers;

import com.myy803.social_bookstore.domain.commands.RegisterCommand;
import com.myy803.social_bookstore.domain.formsdata.RegisterFormData;
import com.myy803.social_bookstore.services.RegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterUseCase registerUseCase;

    @GetMapping("/register")
    String register(Model model) {
        model.addAttribute("registerFormData", new RegisterFormData());
        return "auth/register";
    }

    @PostMapping("/register")
    String register(@ModelAttribute("registerFormData") RegisterFormData registerFormData, Model model) {
        RegisterCommand command = new RegisterCommand(registerFormData.getUsername(), registerFormData.getPassword());
        if (registerUseCase.register(command)) model.addAttribute("message", "User Successfully Registered!");
        else model.addAttribute("message", "User Failed To Register!");
        return "auth/register";
    }
}
