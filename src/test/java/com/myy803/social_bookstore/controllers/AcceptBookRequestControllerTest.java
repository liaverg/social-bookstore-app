package com.myy803.social_bookstore.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(
        statements = {
            "insert into users (username, password, role) values ('username', 'encodedPassword', 'USER');",
            "insert into user_profiles (user_id, full_name, address, age, phone_number) "
                    + "values (1, 'John Doe', '123 Main St', '30', '123-456-7890');",
            "insert into book_categories (category) values ('Fiction'), ('Non-Fiction');",
            "insert into authors (name) values ('Author 1'), ('Author 2');",
            "insert into books (user_profile_id, title, book_category_id, summary) "
                    + "values (1, 'Book Title 1', 1, 'Summary of Book 1'), (1, 'Book Title 2', 2, 'Summary of Book 2');",
            "insert into book_requesting_users (book_id, user_profile_id, status) values (1, 1, 'PENDING'), (1, 1, 'PENDING');"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AcceptBookRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String USERNAME = "username";
    private static final Long BOOK_REQUEST_ID = 1L;
    private static final Long BOOK_ID = 1L;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Accept Book Request")
    void should_accept_book_request() throws Exception {
        Principal principal = () -> USERNAME;

        mockMvc.perform(post("/book-offers/requests")
                        .principal(principal)
                        .param("bookRequestId", String.valueOf(BOOK_REQUEST_ID))
                        .param("bookId", String.valueOf(BOOK_ID)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book-offers/" + BOOK_ID));

        assertThat(
                jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM book_requesting_users WHERE book_id = ? AND status = 'APPROVED'",
                        Integer.class,
                        BOOK_ID),
                is(1));

        assertThat(
                jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM book_requesting_users WHERE book_id = ? AND status = 'DENIED'",
                        Integer.class,
                        BOOK_ID),
                is(1));
    }
}
