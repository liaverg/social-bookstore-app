package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.views.UserProfileView;

public interface ViewUserProfileUseCase {
    UserProfileView viewUserProfile(String username);
}
