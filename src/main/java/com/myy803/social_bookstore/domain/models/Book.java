package com.myy803.social_bookstore.domain.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_category_id", nullable = false)
    private BookCategory bookCategory;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    public Book(
            UserProfile userProfile, String title, String summary, BookCategory bookCategory, List<Author> authors) {
        this.id = null;
        this.userProfile = userProfile;
        this.title = title;
        this.summary = summary;
        this.bookCategory = bookCategory;
        this.authors = authors;
    }
}
