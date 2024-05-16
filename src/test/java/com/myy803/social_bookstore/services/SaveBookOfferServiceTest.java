package com.myy803.social_bookstore.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.myy803.social_bookstore.domain.commands.SaveBookOfferCommand;
import com.myy803.social_bookstore.domain.models.Author;
import com.myy803.social_bookstore.domain.models.Book;
import com.myy803.social_bookstore.domain.models.BookCategory;
import com.myy803.social_bookstore.domain.models.UserProfile;
import com.myy803.social_bookstore.mappers.*;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveBookOfferServiceTest {

    @InjectMocks
    private SaveBookOfferService saveBookOfferService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserProfileMapper userProfileMapper;

    @Mock
    private BookCategoryMapper bookCategoryMapper;

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private BookMapper bookMapper;

    @Test
    @DisplayName("Save Book Offer")
    void should_save_book_offer() {
        SaveBookOfferCommand command =
                new SaveBookOfferCommand("testUser", "Book Title", 1L, Arrays.asList(1L, 2L), "Summary");
        UserProfile userProfile = new UserProfile();
        when(userMapper.findIdByUsername("testUser")).thenReturn(1L);
        when(userProfileMapper.findByUserId(1L)).thenReturn(userProfile);
        when(bookCategoryMapper.findById(1L)).thenReturn(Optional.of(new BookCategory()));
        when(authorMapper.findAllById(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(new Author(), new Author()));

        saveBookOfferService.saveBookOffer(command);

        verify(userMapper).findIdByUsername("testUser");
        verify(userProfileMapper).findByUserId(1L);
        verify(bookCategoryMapper).findById(1L);
        verify(authorMapper).findAllById(Arrays.asList(1L, 2L));
        verify(bookMapper).save(any(Book.class));
    }

    @Test
    @DisplayName("Throw Exception when Book Category Not Found")
    void should_throw_exception_when_book_category_not_found() {
        SaveBookOfferCommand command =
                new SaveBookOfferCommand("testUser", "Book Title", 1L, Arrays.asList(1L, 2L), "Summary");

        when(userMapper.findIdByUsername("testUser")).thenReturn(1L);
        when(userProfileMapper.findByUserId(1L)).thenReturn(new UserProfile());
        when(bookCategoryMapper.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> saveBookOfferService.saveBookOffer(command));

        verify(userMapper).findIdByUsername("testUser");
        verify(userProfileMapper).findByUserId(1L);
        verify(bookCategoryMapper).findById(1L);
        verify(authorMapper, never()).findAllById(any());
        verify(bookMapper, never()).save(any());
    }
}
