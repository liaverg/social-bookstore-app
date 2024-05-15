package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.*;

import com.myy803.social_bookstore.domain.formsdata.UserProfileFormData;
import com.myy803.social_bookstore.services.ViewAuthorsUseCase;
import com.myy803.social_bookstore.services.ViewBookCategoriesUseCase;
import com.myy803.social_bookstore.services.ViewUserProfileUseCase;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewUserProfileController {
    private final ViewUserProfileUseCase viewUserProfileUseCase;
    private final ViewBookCategoriesUseCase viewBookCategoriesUseCase;
    private final ViewAuthorsUseCase viewAuthorsUseCase;

    @GetMapping(USER_PROFILE_PATH)
    String viewUserProfile(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("profile", viewUserProfileUseCase.viewUserProfile(principal.getName()));
        model.addAttribute("allBookCategories", viewBookCategoriesUseCase.viewBookCategories());
        model.addAttribute("allAuthors", viewAuthorsUseCase.viewAuthors());
        model.addAttribute("userProfileFormData", new UserProfileFormData());
        return "profile";
    }
}
