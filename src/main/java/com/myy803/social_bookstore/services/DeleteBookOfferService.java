package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.mappers.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteBookOfferService implements DeleteBookOfferUseCase {
    private final BookMapper bookMapper;

    @Override
    public void deleteBookOffer(Long bookId) {
        bookMapper.deleteById(bookId);
    }
}
