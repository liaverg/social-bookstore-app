package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.LOGIN_PATH;
import static com.myy803.social_bookstore.config.EndpointConfig.REGISTER_PATH;

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

    @GetMapping(REGISTER_PATH)
    String register(Model model) {
        model.addAttribute("registerFormData", new RegisterFormData());
        return "auth/register";
    }

    @PostMapping(REGISTER_PATH)
    String register(@ModelAttribute("registerFormData") RegisterFormData registerFormData, Model model) {
        RegisterCommand command = new RegisterCommand(registerFormData.getUsername(), registerFormData.getPassword());
        if (registerUseCase.register(command)) {
            return "redirect:" + LOGIN_PATH;
        }
        model.addAttribute("message", "User Failed To Register!");
        return "auth/register";
    }
}
