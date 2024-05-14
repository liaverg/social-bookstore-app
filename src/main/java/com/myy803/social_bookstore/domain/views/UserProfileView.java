package com.myy803.social_bookstore.domain.views;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileView {
    private String fullName;
    private String address;
    private String age;
    private String phoneNumber;
    private List<BookCategoryView> favoriteBookCategories;
    // favorite authors
}
