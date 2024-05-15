package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryMapper extends JpaRepository<BookCategory, Long> {}
