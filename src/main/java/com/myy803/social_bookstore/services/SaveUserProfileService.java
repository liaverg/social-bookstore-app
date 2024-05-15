package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.commands.SaveUserProfileCommand;
import com.myy803.social_bookstore.domain.models.Author;
import com.myy803.social_bookstore.domain.models.BookCategory;
import com.myy803.social_bookstore.domain.models.UserProfile;
import com.myy803.social_bookstore.mappers.AuthorMapper;
import com.myy803.social_bookstore.mappers.BookCategoryMapper;
import com.myy803.social_bookstore.mappers.UserMapper;
import com.myy803.social_bookstore.mappers.UserProfileMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveUserProfileService implements SaveUserProfileUseCase {
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private final BookCategoryMapper bookCategoryMapper;
    private final AuthorMapper authorMapper;

    @Override
    public void saveUserProfile(SaveUserProfileCommand command) {
        Long userId = userMapper.findIdByUsername(command.username());
        UserProfile userProfile = userProfileMapper.findByUserId(userId);
        List<BookCategory> bookCategories = bookCategoryMapper.findAllById(command.favoriteBookCategoriesIds());
        List<Author> authors = authorMapper.findAllById(command.favoriteAuthorsIds());

        userProfile.update(
                command.fullName(), command.address(), command.age(), command.phoneNumber(), bookCategories, authors);
    }
}
