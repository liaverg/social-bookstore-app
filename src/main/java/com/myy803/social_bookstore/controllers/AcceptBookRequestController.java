package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.*;

import com.myy803.social_bookstore.services.AcceptBookRequestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AcceptBookRequestController {
    private final AcceptBookRequestUseCase acceptBookRequestUseCase;

    @PostMapping(ACCEPT_REQUESTS_PATH)
    String acceptBookRequest(Long bookRequestId, Long bookId) {
        acceptBookRequestUseCase.acceptBookRequest(bookRequestId);
        return "redirect:/book-offers/" + bookId;
    }
}
