package com.myy803.social_bookstore.domain.commands;

import java.util.List;

public record SaveUserProfileCommand(
        String username,
        String fullName,
        String address,
        String age,
        String phoneNumber,
        List<Long> favoriteBookCategoriesIds,
        List<Long> favoriteAuthorsIds) {
    public SaveUserProfileCommand(
            String username,
            String fullName,
            String address,
            String age,
            String phoneNumber,
            List<Long> favoriteBookCategoriesIds,
            List<Long> favoriteAuthorsIds) {
        this.username = username;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.favoriteBookCategoriesIds = favoriteBookCategoriesIds == null ? List.of() : favoriteBookCategoriesIds;
        this.favoriteAuthorsIds = favoriteAuthorsIds == null ? List.of() : favoriteAuthorsIds;
    }
}
