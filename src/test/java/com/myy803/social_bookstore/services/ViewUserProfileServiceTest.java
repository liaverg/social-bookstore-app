package com.myy803.social_bookstore.services;

import static org.mockito.Mockito.when;

import com.myy803.social_bookstore.domain.models.*;
import com.myy803.social_bookstore.domain.views.UserProfileView;
import com.myy803.social_bookstore.mappers.UserMapper;
import com.myy803.social_bookstore.mappers.UserProfileMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ViewUserProfileServiceTest {

    @InjectMocks
    private ViewUserProfileService viewUserProfileService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserProfileMapper userProfileMapper;

    @Test
    @DisplayName("View User Profile")
    void should_view_user_profile() {
        String username = "testUser";
        User user = new User(username, "password", Role.USER);
        UserProfile userProfile = user.getUserProfile();
        userProfile.setFullName("John Doe");
        userProfile.setAddress("123 Main St");
        userProfile.setAge("30");
        userProfile.setPhoneNumber("1234567890");
        when(userMapper.findIdByUsername(username)).thenReturn(1L);
        when(userProfileMapper.findByUserId(1L)).thenReturn(userProfile);
        BookCategory category1 = new BookCategory(1L, "Category 1");
        BookCategory category2 = new BookCategory(2L, "Category 2");
        List<BookCategory> favoriteCategories = Arrays.asList(category1, category2);
        userProfile.setFavoriteBookCategories(favoriteCategories);

        Author author1 = new Author(1L, "Author 1");
        Author author2 = new Author(2L, "Author 2");
        List<Author> favoriteAuthors = Arrays.asList(author1, author2);
        userProfile.setFavoriteAuthors(favoriteAuthors);

        UserProfileView userProfileView = viewUserProfileService.viewUserProfile(username);

        Assertions.assertEquals("John Doe", userProfileView.getFullName());
        Assertions.assertEquals("123 Main St", userProfileView.getAddress());
        Assertions.assertEquals("30", userProfileView.getAge());
        Assertions.assertEquals("1234567890", userProfileView.getPhoneNumber());

        Assertions.assertEquals(2, userProfileView.getFavoriteBookCategories().size());
        Assertions.assertEquals(
                "Category 1", userProfileView.getFavoriteBookCategories().get(0).getCategory());
        Assertions.assertEquals(
                "Category 2", userProfileView.getFavoriteBookCategories().get(1).getCategory());

        Assertions.assertEquals(2, userProfileView.getFavoriteAuthors().size());
        Assertions.assertEquals(
                "Author 1", userProfileView.getFavoriteAuthors().get(0).getName());
        Assertions.assertEquals(
                "Author 2", userProfileView.getFavoriteAuthors().get(1).getName());
    }
}
