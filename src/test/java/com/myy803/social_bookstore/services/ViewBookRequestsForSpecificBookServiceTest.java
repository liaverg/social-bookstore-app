package com.myy803.social_bookstore.services;

import static org.mockito.Mockito.when;

import com.myy803.social_bookstore.domain.models.*;
import com.myy803.social_bookstore.domain.views.BookRequestView;
import com.myy803.social_bookstore.mappers.BookRequestMapper;
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
public class ViewBookRequestsForSpecificBookServiceTest {

    @InjectMocks
    private ViewBookRequestsForSpecificBookService viewBookRequestsForSpecificBookService;

    @Mock
    private BookRequestMapper bookRequestMapper;

    @Test
    @DisplayName("Find Book Requests for Specific Book")
    void should_find_book_requests_for_specific_book() {
        UserProfile userProfile = new UserProfile();
        User user = new User();
        user.setUsername("testUser");
        userProfile.setUser(user);
        Book book = new Book();
        book.setId(1L);

        BookRequest bookRequest1 = new BookRequest();
        bookRequest1.setId(1L);
        bookRequest1.setStatus(RequestStatus.PENDING);
        bookRequest1.setBook(book);
        bookRequest1.setUserProfile(userProfile);
        BookRequest bookRequest2 = new BookRequest();
        bookRequest2.setId(2L);
        bookRequest2.setStatus(RequestStatus.APPROVED);
        bookRequest2.setBook(book);
        bookRequest2.setUserProfile(userProfile);
        List<BookRequest> bookRequests = Arrays.asList(bookRequest1, bookRequest2);

        when(bookRequestMapper.findByBookId(1L)).thenReturn(bookRequests);

        List<BookRequestView> bookRequestViews = viewBookRequestsForSpecificBookService.findBookRequestsForBookId(1L);

        Assertions.assertNotNull(bookRequestViews);
        Assertions.assertEquals(2, bookRequestViews.size());

        BookRequestView firstRequestView = bookRequestViews.get(0);
        Assertions.assertEquals(1L, firstRequestView.getId());
        Assertions.assertEquals(
                1L, firstRequestView.getBookOfferId()); // Ensure bookOfferId represents the ID of the associated book
        Assertions.assertEquals("PENDING", firstRequestView.getStatus());

        BookRequestView secondRequestView = bookRequestViews.get(1);
        Assertions.assertEquals(2L, secondRequestView.getId());
        Assertions.assertEquals(
                1L, secondRequestView.getBookOfferId()); // Ensure bookOfferId represents the ID of the associated book
        Assertions.assertEquals("APPROVED", secondRequestView.getStatus());
    }
}
