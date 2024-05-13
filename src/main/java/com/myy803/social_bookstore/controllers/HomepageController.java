package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.HOMEPAGE_PATH;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomepageController {

    @GetMapping(HOMEPAGE_PATH)
    String homepage() {
        return "homepage";
    }
}
