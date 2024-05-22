package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.CONTACT_DETAILS_PATH;

import com.myy803.social_bookstore.domain.views.ContactDetailsView;
import com.myy803.social_bookstore.services.ViewContactDetailsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ViewContactDetailsController {
    private final ViewContactDetailsUseCase viewContactDetailsUseCase;

    @GetMapping(CONTACT_DETAILS_PATH)
    String viewContactDetails(
            @RequestParam("bookId") Long bookId,
            @RequestParam("requestingUserUsername") String requestingUserUsername,
            Model model) {
        ContactDetailsView contactDetailsView =
                viewContactDetailsUseCase.findContactDetailsByUsername(requestingUserUsername);
        model.addAttribute("contactDetails", contactDetailsView);
        model.addAttribute("bookId", bookId);
        return "books/view-contact-details";
    }
}
