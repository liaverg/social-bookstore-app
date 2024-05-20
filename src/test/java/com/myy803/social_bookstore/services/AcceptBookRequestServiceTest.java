package com.myy803.social_bookstore.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.myy803.social_bookstore.domain.models.*;
import com.myy803.social_bookstore.mappers.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AcceptBookRequestServiceTest {

    @InjectMocks
    private AcceptBookRequestService acceptBookRequestService;

    @Mock
    private BookRequestMapper bookRequestMapper;

    private static final Long BOOK_REQUEST_ID_1 = 1L;
    private static final Long BOOK_REQUEST_ID_2 = 2L;

    @Test
    @DisplayName("Accept Book Request")
    void should_accept_book_request() {
        BookRequest acceptedBookRequest = mock(BookRequest.class);
        BookRequest deniedBookRequest = mock(BookRequest.class);
        Book book = mock(Book.class);

        // Set up interactions and behaviors
        when(acceptedBookRequest.getId()).thenReturn(BOOK_REQUEST_ID_1);
        when(deniedBookRequest.getId()).thenReturn(BOOK_REQUEST_ID_2);
        when(acceptedBookRequest.getBook()).thenReturn(book);
        when(bookRequestMapper.findById(BOOK_REQUEST_ID_1)).thenReturn(Optional.of(acceptedBookRequest));
        when(book.getBookRequests()).thenReturn(List.of(acceptedBookRequest, deniedBookRequest));

        // Call the method under test
        acceptBookRequestService.acceptBookRequest(BOOK_REQUEST_ID_1);

        // Verify interactions
        verify(acceptedBookRequest).setStatus(RequestStatus.APPROVED);
        verify(deniedBookRequest).setStatus(RequestStatus.DENIED);
    }

    @Test
    @DisplayName("Throw Exception when Book Request Not Found")
    void should_throw_exception_when_book_request_not_found() {
        Long bookRequestId = 1L;

        when(bookRequestMapper.findById(bookRequestId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> acceptBookRequestService.acceptBookRequest(bookRequestId));

        verify(bookRequestMapper).findById(bookRequestId);
    }
}
