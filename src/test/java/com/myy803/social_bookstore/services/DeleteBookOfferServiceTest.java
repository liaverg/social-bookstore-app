package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.models.Book;
import com.myy803.social_bookstore.domain.models.BookRequest;
import com.myy803.social_bookstore.domain.models.RequestStatus;
import com.myy803.social_bookstore.mappers.BookMapper;
import com.myy803.social_bookstore.mappers.BookRequestMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteBookOfferServiceTest {

    @InjectMocks
    private DeleteBookOfferService deleteBookOfferService;

    @Mock
    private BookMapper bookMapper;

    @Test
    @DisplayName("Delete Book Offer")
    void should_delete_book_offer() {
        Long bookId = 1L;

        deleteBookOfferService.deleteBookOffer(bookId);

        verify(bookMapper, times(1)).deleteById(bookId);
    }

    @Test
    @DisplayName("Throw Exception when Deletion Fails")
    void should_throw_exception_when_deletion_fails() {
        Long bookId = 1L;

        doThrow(new RuntimeException("Deletion failed")).when(bookMapper).deleteById(bookId);

        assertThrows(RuntimeException.class, () -> deleteBookOfferService.deleteBookOffer(bookId));

        verify(bookMapper, times(1)).deleteById(bookId);
    }
}
