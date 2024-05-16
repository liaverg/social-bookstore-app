package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.models.Author;
import com.myy803.social_bookstore.domain.models.Book;
import com.myy803.social_bookstore.domain.models.UserProfile;
import com.myy803.social_bookstore.domain.views.BookOfferView;
import com.myy803.social_bookstore.mappers.UserMapper;
import com.myy803.social_bookstore.mappers.UserProfileMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ViewBookOffersService implements ViewBookOffersUseCase {
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;

    @Override
    public List<BookOfferView> viewBookOffers(String username) {
        Long userId = userMapper.findIdByUsername(username);
        UserProfile userProfile = userProfileMapper.findByUserId(userId);
        List<Book> bookOffers = userProfile.getBookOffers();
        return bookOffers.stream()
                .map(book -> new BookOfferView(
                        book.getId(),
                        username,
                        book.getTitle(),
                        book.getBookCategory().getCategory(),
                        book.getAuthors().stream().map(Author::getName).toList(),
                        book.getSummary()))
                .toList();
    }
}
