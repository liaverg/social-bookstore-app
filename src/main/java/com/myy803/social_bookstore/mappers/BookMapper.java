package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMapper extends JpaRepository<Book, Long> {}
