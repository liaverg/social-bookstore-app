package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.*;

import com.myy803.social_bookstore.domain.commands.SaveBookOfferCommand;
import com.myy803.social_bookstore.domain.formsdata.SaveBookOfferFormData;
import com.myy803.social_bookstore.services.SaveBookOfferUseCase;
import com.myy803.social_bookstore.services.ViewAuthorsUseCase;
import com.myy803.social_bookstore.services.ViewBookCategoriesUseCase;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SaveBookOfferController {
    private final ViewBookCategoriesUseCase viewBookCategoriesUseCase;
    private final ViewAuthorsUseCase viewAuthorsUseCase;
    private final SaveBookOfferUseCase saveBookOfferUseCase;

    @GetMapping(BOOK_OFFERS_SAVE_PATH)
    String saveBookOffer(Model model) {
        model.addAttribute("saveBookOfferFormData", new SaveBookOfferFormData());
        model.addAttribute("allBookCategories", viewBookCategoriesUseCase.viewBookCategories());
        model.addAttribute("allAuthors", viewAuthorsUseCase.viewAuthors());
        return "books/book-offer-form";
    }

    @PostMapping(BOOK_OFFERS_SAVE_PATH)
    String saveBookOffer(
            @ModelAttribute("saveBookOfferFormData") SaveBookOfferFormData saveBookOfferFormData, Principal principal) {
        SaveBookOfferCommand command = new SaveBookOfferCommand(
                principal.getName(),
                saveBookOfferFormData.getBookTitle(),
                saveBookOfferFormData.getBookCategoryId(),
                saveBookOfferFormData.getAuthorsIds(),
                saveBookOfferFormData.getSummary());
        saveBookOfferUseCase.saveBookOffer(command);
        return "redirect:" + HOMEPAGE_PATH;
    }
}
