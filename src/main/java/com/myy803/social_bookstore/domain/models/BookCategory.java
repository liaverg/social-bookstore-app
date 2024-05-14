package com.myy803.social_bookstore.domain.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "book_categories")
public class BookCategory {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String category;
}
