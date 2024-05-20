package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.BookRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRequestMapper extends JpaRepository<BookRequest, Long> {
    @Query("SELECT br FROM BookRequest br WHERE br.book.id = :bookOfferId")
    List<BookRequest> findByBookId(Long bookOfferId);
}
