package com.myy803.social_bookstore.domain.commands;

import java.util.List;

public record SaveBookOfferCommand(
        String username, String bookTitle, Long bookCategoryId, List<Long> authorsIds, String summary) {
    public SaveBookOfferCommand(
            String username, String bookTitle, Long bookCategoryId, List<Long> authorsIds, String summary) {
        this.username = username;
        this.bookTitle = bookTitle;
        this.bookCategoryId = bookCategoryId;
        this.authorsIds = authorsIds == null ? List.of() : authorsIds;
        this.summary = summary;
    }
}
