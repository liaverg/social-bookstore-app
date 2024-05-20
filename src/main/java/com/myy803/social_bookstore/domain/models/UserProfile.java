package com.myy803.social_bookstore.domain.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_profiles")
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Setter
    @Getter
    @Column(name = "full_name")
    private String fullName;

    @Setter
    @Getter
    private String address;

    @Setter
    @Getter
    private String age;

    @Setter
    @Getter
    @Column(name = "phone_number")
    private String phoneNumber;

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_profile_favorite_book_categories",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "book_category_id"))
    private List<BookCategory> favoriteBookCategories;

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_profile_favorite_authors",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> favoriteAuthors;

    @Setter
    @Getter
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookOffers;

    @Setter
    @Getter
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookRequest> bookRequests;

    public UserProfile(User user) {
        this.id = null;
        this.user = user;
    }

    public void update(
            String fullName,
            String address,
            String age,
            String phoneNumber,
            List<BookCategory> favoriteBookCategories,
            List<Author> favoriteAuthors) {
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.favoriteBookCategories = favoriteBookCategories;
        this.favoriteAuthors = favoriteAuthors;
    }
}
