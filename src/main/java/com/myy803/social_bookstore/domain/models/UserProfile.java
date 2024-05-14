package com.myy803.social_bookstore.domain.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_profiles")
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "full_name")
    private String fullName;

    private String age;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_profile_favorite_book_categories",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "book_category_id"))
    private List<BookCategory> favoriteBookCategories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_profile_favorite_authors",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> favoriteAuthors;

    public UserProfile(User user) {
        this.id = null;
        this.user = user;
    }
}
