package com.myy803.social_bookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomepageController {

    @GetMapping("/homepage")
    String homepage() {
        return "homepage";
    }
}
