package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.BOOK_OFFERS_VIEW_PATH;

import com.myy803.social_bookstore.services.ViewBookOffersUseCase;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewBookOffersController {
    private final ViewBookOffersUseCase viewBookOffersUseCase;

    @GetMapping(BOOK_OFFERS_VIEW_PATH)
    String viewBookOffers(Principal principal, Model model) {
        model.addAttribute("allBookOffers", viewBookOffersUseCase.viewBookOffers(principal.getName()));
        return "books/view-book-offers";
    }
}
