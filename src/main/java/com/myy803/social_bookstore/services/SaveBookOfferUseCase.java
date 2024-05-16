package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.commands.SaveBookOfferCommand;

public interface SaveBookOfferUseCase {
    void saveBookOffer(SaveBookOfferCommand command);
}
