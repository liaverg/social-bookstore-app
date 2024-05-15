package com.myy803.social_bookstore.services;

import static org.mockito.Mockito.*;

import com.myy803.social_bookstore.domain.commands.SaveUserProfileCommand;
import com.myy803.social_bookstore.domain.models.Author;
import com.myy803.social_bookstore.domain.models.BookCategory;
import com.myy803.social_bookstore.domain.models.UserProfile;
import com.myy803.social_bookstore.mappers.AuthorMapper;
import com.myy803.social_bookstore.mappers.BookCategoryMapper;
import com.myy803.social_bookstore.mappers.UserMapper;
import com.myy803.social_bookstore.mappers.UserProfileMapper;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveUserProfileServiceTest {

    @InjectMocks
    private SaveUserProfileService saveUserProfileService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserProfileMapper userProfileMapper;

    @Mock
    private BookCategoryMapper bookCategoryMapper;

    @Mock
    private AuthorMapper authorMapper;

    @Test
    @DisplayName("Save User Profile")
    void should_save_user_profile() {
        SaveUserProfileCommand command = new SaveUserProfileCommand(
                "testUser",
                "John Doe",
                "123 Main St",
                "30",
                "123-456-7890",
                Arrays.asList(1L, 2L),
                Arrays.asList(1L, 2L));

        when(userMapper.findIdByUsername(command.username())).thenReturn(1L);
        when(userProfileMapper.findByUserId(1L)).thenReturn(new UserProfile());
        when(bookCategoryMapper.findAllById(command.favoriteBookCategoriesIds()))
                .thenReturn(Arrays.asList(new BookCategory(), new BookCategory()));
        when(authorMapper.findAllById(command.favoriteAuthorsIds()))
                .thenReturn(Arrays.asList(new Author(), new Author()));

        saveUserProfileService.saveUserProfile(command);

        verify(userProfileMapper).findByUserId(1L);
        verify(bookCategoryMapper).findAllById(Arrays.asList(1L, 2L));
        verify(authorMapper).findAllById(Arrays.asList(1L, 2L));
    }
}
