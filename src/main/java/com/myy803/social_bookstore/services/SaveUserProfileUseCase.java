package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.commands.SaveUserProfileCommand;

public interface SaveUserProfileUseCase {
    void saveUserProfile(SaveUserProfileCommand command);
}
