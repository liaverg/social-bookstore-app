package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.views.BookOfferView;
import java.util.List;

public interface ViewBookOffersUseCase {
    List<BookOfferView> viewBookOffers(String username);
}
