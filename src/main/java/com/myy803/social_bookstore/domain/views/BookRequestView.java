package com.myy803.social_bookstore.domain.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestView {
    Long id;
    Long bookOfferId;
    String bookTitle;
    String requestingUserUsername;
    String status;
}
