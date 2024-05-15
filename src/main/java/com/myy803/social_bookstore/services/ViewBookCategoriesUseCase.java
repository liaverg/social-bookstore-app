package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.views.BookCategoryView;
import java.util.List;

public interface ViewBookCategoriesUseCase {
    List<BookCategoryView> viewBookCategories();
}
