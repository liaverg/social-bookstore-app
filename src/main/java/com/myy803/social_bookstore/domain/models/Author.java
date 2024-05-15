package com.myy803.social_bookstore.domain.models;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
