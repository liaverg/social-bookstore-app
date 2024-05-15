package com.myy803.social_bookstore.services;

import static org.mockito.Mockito.when;

import com.myy803.social_bookstore.domain.models.BookCategory;
import com.myy803.social_bookstore.domain.views.BookCategoryView;
import com.myy803.social_bookstore.mappers.BookCategoryMapper;
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
public class ViewBookCategoriesServiceTest {

    @InjectMocks
    private ViewBookCategoriesService viewBookCategoriesService;

    @Mock
    private BookCategoryMapper bookCategoryMapper;

    @Test
    @DisplayName("View Book Categories")
    void should_view_book_categories() {
        BookCategory category1 = new BookCategory(1L, "Category 1");
        BookCategory category2 = new BookCategory(2L, "Category 2");
        when(bookCategoryMapper.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<BookCategoryView> categoryViews = viewBookCategoriesService.viewBookCategories();

        Assertions.assertEquals(2, categoryViews.size());
        Assertions.assertEquals("Category 1", categoryViews.get(0).getCategory());
        Assertions.assertEquals("Category 2", categoryViews.get(1).getCategory());
    }
}
