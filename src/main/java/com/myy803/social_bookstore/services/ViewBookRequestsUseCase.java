package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.views.BookRequestView;
import java.util.List;

public interface ViewBookRequestsUseCase {
    List<BookRequestView> findBookRequestsForBookOfferId(Long bookOfferId);
}
