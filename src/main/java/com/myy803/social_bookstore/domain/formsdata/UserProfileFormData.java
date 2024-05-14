package com.myy803.social_bookstore.domain.formsdata;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileFormData {
    private String fullName;
    private String address;
    private String age;
    private String phoneNumber;
    private List<Long> favoriteBookCategoriesIds;
    // favorite authors
}
