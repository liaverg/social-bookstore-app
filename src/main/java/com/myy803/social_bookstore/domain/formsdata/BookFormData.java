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
public class BookFormData {
    private String bookTitle;
    private Long bookCategoryId;
    private List<Long> authorsIds;
    private String summary;
}
