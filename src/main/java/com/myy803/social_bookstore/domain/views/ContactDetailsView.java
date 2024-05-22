package com.myy803.social_bookstore.domain.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsView {
    private String username;
    private String fullName;
    private String address;
    private String phoneNumber;
}
