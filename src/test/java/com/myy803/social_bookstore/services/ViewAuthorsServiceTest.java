package com.myy803.social_bookstore.services;

import static org.mockito.Mockito.when;

import com.myy803.social_bookstore.domain.models.Author;
import com.myy803.social_bookstore.domain.views.AuthorView;
import com.myy803.social_bookstore.mappers.AuthorMapper;
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
public class ViewAuthorsServiceTest {

    @InjectMocks
    private ViewAuthorsService viewAuthorsService;

    @Mock
    private AuthorMapper authorMapper;

    @Test
    @DisplayName("View Authors")
    void should_view_authors() {
        // Given
        Author author1 = new Author(1L, "Author 1");
        Author author2 = new Author(2L, "Author 2");
        when(authorMapper.findAll()).thenReturn(Arrays.asList(author1, author2));

        // When
        List<AuthorView> authorViews = viewAuthorsService.viewAuthors();

        // Then
        Assertions.assertEquals(2, authorViews.size());
        Assertions.assertEquals("Author 1", authorViews.get(0).getName());
        Assertions.assertEquals("Author 2", authorViews.get(1).getName());
    }
}
