package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.models.Book;
import com.myy803.social_bookstore.domain.models.BookRequest;
import com.myy803.social_bookstore.domain.models.RequestStatus;
import com.myy803.social_bookstore.mappers.*;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AcceptBookRequestService implements AcceptBookRequestUseCase {
    private final BookRequestMapper bookRequestMapper;

    @Override
    public void acceptBookRequest(Long bookRequestId) {
        BookRequest acceptedBookRequest = bookRequestMapper
                .findById(bookRequestId)
                .orElseThrow(() -> new IllegalArgumentException("Book request not found"));
        acceptedBookRequest.setStatus(RequestStatus.APPROVED);
        Book book = acceptedBookRequest.getBook();
        book.getBookRequests().stream()
                .filter(bookRequest -> !Objects.equals(bookRequest.getId(), bookRequestId))
                .forEach(bookRequest -> bookRequest.setStatus(RequestStatus.DENIED));
    }
}
