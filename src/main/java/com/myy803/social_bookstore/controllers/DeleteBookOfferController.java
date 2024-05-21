package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.BOOK_OFFERS_DELETE_PATH;

import com.myy803.social_bookstore.services.DeleteBookOfferUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DeleteBookOfferController {
    private final DeleteBookOfferUseCase deleteBookOfferUseCase;

    @PostMapping(BOOK_OFFERS_DELETE_PATH)
    String deleteBookOffer(@PathVariable Long bookId) {
        deleteBookOfferUseCase.deleteBookOffer(bookId);
        return "redirect:/book-offers";
    }
}
