package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.views.AuthorView;
import java.util.List;

public interface ViewAuthorsUseCase {
    List<AuthorView> viewAuthors();
}
