package com.myy803.social_bookstore.domain.models;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "book_categories")
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
}
