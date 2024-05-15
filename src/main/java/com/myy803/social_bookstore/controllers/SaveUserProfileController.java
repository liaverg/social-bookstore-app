package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.USER_PROFILE_PATH;

import com.myy803.social_bookstore.domain.commands.SaveUserProfileCommand;
import com.myy803.social_bookstore.domain.formsdata.UserProfileFormData;
import com.myy803.social_bookstore.services.SaveUserProfileUseCase;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SaveUserProfileController {
    private final SaveUserProfileUseCase saveUserProfileUseCase;

    @PostMapping(USER_PROFILE_PATH)
    String saveUserProfile(
            @ModelAttribute("userProfileFormData") UserProfileFormData userProfileFormData,
            Principal principal) {
        SaveUserProfileCommand command = new SaveUserProfileCommand(
                principal.getName(),
                userProfileFormData.getFullName(),
                userProfileFormData.getAddress(),
                userProfileFormData.getAge(),
                userProfileFormData.getPhoneNumber(),
                userProfileFormData.getFavoriteBookCategoriesIds(),
                userProfileFormData.getFavoriteAuthorsIds());
        saveUserProfileUseCase.saveUserProfile(command);
        return "redirect:" + USER_PROFILE_PATH;
    }
}
