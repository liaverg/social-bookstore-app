package com.myy803.social_bookstore.domain.views;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookOfferView {
    Long id;
    String bookSupplierUsername;
    String title;
    String category;
    List<String> authors;
    String summary;
}
