package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.models.UserProfile;
import com.myy803.social_bookstore.domain.views.ContactDetailsView;
import com.myy803.social_bookstore.mappers.UserMapper;
import com.myy803.social_bookstore.mappers.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ViewContactDetailsService implements ViewContactDetailsUseCase {
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;

    @Override
    public ContactDetailsView findContactDetailsByUsername(String username) {
        Long userId = userMapper.findIdByUsername(username);
        UserProfile userProfile = userProfileMapper.findByUserId(userId);
        return new ContactDetailsView(
                username, userProfile.getFullName(), userProfile.getAddress(), userProfile.getPhoneNumber());
    }
}
