package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.commands.SaveBookOfferCommand;
import com.myy803.social_bookstore.domain.models.Author;
import com.myy803.social_bookstore.domain.models.Book;
import com.myy803.social_bookstore.domain.models.BookCategory;
import com.myy803.social_bookstore.domain.models.UserProfile;
import com.myy803.social_bookstore.mappers.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveBookOfferService implements SaveBookOfferUseCase {
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private final BookCategoryMapper bookCategoryMapper;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @Override
    public void saveBookOffer(SaveBookOfferCommand command) {
        Long userId = userMapper.findIdByUsername(command.username());
        UserProfile userProfile = userProfileMapper.findByUserId(userId);
        BookCategory bookCategory = bookCategoryMapper
                .findById(command.bookCategoryId())
                .orElseThrow(() -> new RuntimeException("Book Category Not Found"));
        List<Author> authors = authorMapper.findAllById(command.authorsIds());

        Book book = new Book(userProfile, command.bookTitle(), command.summary(), bookCategory, authors);
        bookMapper.save(book);
    }
}
