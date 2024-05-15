package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorMapper extends JpaRepository<Author, Long> {}
