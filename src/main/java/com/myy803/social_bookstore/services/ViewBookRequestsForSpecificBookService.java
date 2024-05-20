package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.models.BookRequest;
import com.myy803.social_bookstore.domain.views.BookRequestView;
import com.myy803.social_bookstore.mappers.BookRequestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ViewBookRequestsForSpecificBookService implements ViewBookRequestsForSpecificBookUseCase {
    private final BookRequestMapper bookRequestMapper;

    @Override
    public List<BookRequestView> findBookRequestsForBookId(Long bookId) {
        List<BookRequest> bookRequests = bookRequestMapper.findByBookId(bookId);
        return bookRequests.stream()
                .map(bookRequest -> new BookRequestView(
                        bookRequest.getId(),
                        bookRequest.getBook().getId(),
                        bookRequest.getBook().getTitle(),
                        bookRequest.getUserProfile().getUser().getUsername(),
                        bookRequest.getStatus().name()))
                .toList();
    }
}
