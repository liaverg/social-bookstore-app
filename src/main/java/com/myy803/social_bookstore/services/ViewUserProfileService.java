package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.models.UserProfile;
import com.myy803.social_bookstore.domain.views.AuthorView;
import com.myy803.social_bookstore.domain.views.BookCategoryView;
import com.myy803.social_bookstore.domain.views.UserProfileView;
import com.myy803.social_bookstore.mappers.UserMapper;
import com.myy803.social_bookstore.mappers.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ViewUserProfileService implements ViewUserProfileUseCase {
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileView viewUserProfile(String username) {
        Long userId = userMapper.findIdByUsername(username);
        UserProfile userProfile = userProfileMapper.findByUserId(userId);
        return new UserProfileView(
                userProfile.getFullName(),
                userProfile.getAddress(),
                userProfile.getAge(),
                userProfile.getPhoneNumber(),
                userProfile.getFavoriteBookCategories().stream()
                        .map(bookCategory -> new BookCategoryView(bookCategory.getId(), bookCategory.getCategory()))
                        .toList(),
                userProfile.getFavoriteAuthors().stream()
                        .map(author -> new AuthorView(author.getId(), author.getName()))
                        .toList());
    }
}
