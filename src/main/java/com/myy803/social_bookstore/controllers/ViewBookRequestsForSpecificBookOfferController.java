package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.BOOK_OFFERS_VIEW_REQUESTS_PATH;

import com.myy803.social_bookstore.domain.views.BookRequestView;
import com.myy803.social_bookstore.services.ViewBookRequestsUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ViewBookRequestsForSpecificBookOfferController {
    private final ViewBookRequestsUseCase viewBookRequestsUseCase;

    @GetMapping(BOOK_OFFERS_VIEW_REQUESTS_PATH)
    String viewBookRequestsForSpecificBook(@PathVariable Long bookId, Model model) {
        List<BookRequestView> bookRequests = viewBookRequestsUseCase.findBookRequestsForBookOfferId(bookId);
        model.addAttribute("bookRequests", bookRequests);
        return "books/view-book-offer-requests";
    }
}
