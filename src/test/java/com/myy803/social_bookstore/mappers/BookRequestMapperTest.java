package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.BookRequest;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(
        statements = {
            "insert into users (username, password, role) values ('username', 'encodedPassword', 'USER');",
            "insert into user_profiles (user_id, full_name, address, age, phone_number) "
                    + "values (1, 'John Doe', '123 Main St', '30', '123-456-7890');",
            "insert into book_categories (category) values ('Fiction'), ('Non-Fiction');",
            "insert into authors (name) values ('Author 1'), ('Author 2');",
            "insert into books (user_profile_id, title, book_category_id, summary) values (1, 'Book Title 1', 1, 'Summary of Book 1');",
            "insert into book_requesting_users (book_id, user_profile_id, status) values (1, 1, 'PENDING');"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BookRequestMapperTest {
    @Autowired
    private BookRequestMapper bookRequestMapper;

    @Test
    @DisplayName("Find Book Requests by Book ID")
    public void should_find_book_requests_by_book_id() {
        List<BookRequest> foundBookRequests = bookRequestMapper.findByBookId(1L);

        Assertions.assertNotNull(foundBookRequests);
        Assertions.assertFalse(foundBookRequests.isEmpty());
        Assertions.assertEquals(1, foundBookRequests.size());
    }

    @Test
    @DisplayName("Find No Book Requests by Non-existing Book ID")
    public void should_find_no_book_requests_by_non_existing_book_id() {
        List<BookRequest> foundBookRequests = bookRequestMapper.findByBookId(999L);

        Assertions.assertNotNull(foundBookRequests);
        Assertions.assertTrue(foundBookRequests.isEmpty());
    }
}
