package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.LOGIN_PATH;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping(LOGIN_PATH)
    String login() {
        return "auth/login";
    }
}
