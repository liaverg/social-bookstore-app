package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.views.BookCategoryView;
import com.myy803.social_bookstore.mappers.BookCategoryMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ViewBookCategoriesService implements ViewBookCategoriesUseCase {
    private final BookCategoryMapper bookCategoryMapper;

    @Override
    public List<BookCategoryView> viewBookCategories() {
        return bookCategoryMapper.findAll().stream()
                .map(bookCategory -> new BookCategoryView(bookCategory.getId(), bookCategory.getCategory()))
                .toList();
    }
}
