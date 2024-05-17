package com.myy803.social_bookstore.services;

import static org.mockito.Mockito.when;

import com.myy803.social_bookstore.domain.models.*;
import com.myy803.social_bookstore.domain.views.BookOfferView;
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
public class ViewBookOffersServiceTest {

    @InjectMocks
    private ViewBookOffersService viewBookOffersService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserProfileMapper userProfileMapper;

    @Test
    @DisplayName("View Book Offers")
    void should_view_book_offers() {
        String username = "testUser";
        Long userId = 1L;
        User user = new User(username, "password", Role.USER);
        UserProfile userProfile = new UserProfile(user);
        BookCategory bookCategory1 = new BookCategory(1L, "Category 1");
        BookCategory bookCategory2 = new BookCategory(2L, "Category 2");
        Author author1 = new Author(1L, "Author 1");
        Author author2 = new Author(2L, "Author 2");
        List<Author> authors = Arrays.asList(author1, author2);
        List<Book> bookOffers = Arrays.asList(
                new Book(userProfile, "Book Title 1", "Summary of Book 1", bookCategory1, authors),
                new Book(userProfile, "Book Title 2", "Summary of Book 2", bookCategory2, authors));
        userProfile.setBookOffers(bookOffers);
        when(userMapper.findIdByUsername(username)).thenReturn(userId);
        when(userProfileMapper.findByUserId(userId)).thenReturn(userProfile);

        // Call the service method
        List<BookOfferView> bookOfferViews = viewBookOffersService.viewBookOffers(username);

        // Assertions
        Assertions.assertEquals(2, bookOfferViews.size());
        Assertions.assertEquals("Book Title 1", bookOfferViews.get(0).getTitle());
        Assertions.assertEquals("Category 1", bookOfferViews.get(0).getCategory());
        Assertions.assertEquals(
                Arrays.asList("Author 1", "Author 2"), bookOfferViews.get(0).getAuthors());
        Assertions.assertEquals("Summary of Book 1", bookOfferViews.get(0).getSummary());

        Assertions.assertEquals("Book Title 2", bookOfferViews.get(1).getTitle());
        Assertions.assertEquals("Category 2", bookOfferViews.get(1).getCategory());
        Assertions.assertEquals(
                Arrays.asList("Author 1", "Author 2"), bookOfferViews.get(1).getAuthors());
        Assertions.assertEquals("Summary of Book 2", bookOfferViews.get(1).getSummary());
    }
}
