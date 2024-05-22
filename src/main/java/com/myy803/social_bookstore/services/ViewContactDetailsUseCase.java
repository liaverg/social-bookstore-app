package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.views.ContactDetailsView;

public interface ViewContactDetailsUseCase {
    ContactDetailsView findContactDetailsByUsername(String username);
}
