package com.myy803.social_bookstore.config;

import com.myy803.social_bookstore.domain.views.AuthorView;
import com.myy803.social_bookstore.domain.views.BookCategoryView;
import java.util.List;

public class ThymeleafUtil {

    public static List<Long> getBookCategoryIds(List<BookCategoryView> bookViews) {
        return bookViews.stream().map(BookCategoryView::getId).toList();
    }

    public static List<Long> getAuthorIds(List<AuthorView> authorViews) {
        return authorViews.stream().map(AuthorView::getId).toList();
    }
}
