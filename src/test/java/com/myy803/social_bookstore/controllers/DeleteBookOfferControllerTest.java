package com.myy803.social_bookstore.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
            "insert into user_profiles (user_id) values (1);",
            "insert into book_categories (category) values ('Fiction'), ('Non-Fiction');",
            "insert into authors (name) values ('Author 1'), ('Author 2');",
            "insert into books (id, user_profile_id, title, book_category_id, summary) values (1, 1, 'Book Title 1', 1, 'Summary of Book 1');",
            "insert into book_requesting_users (book_id, user_profile_id, status) values (1, 1, 'PENDING'), (1, 1, 'APPROVED');"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DeleteBookOfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String USERNAME = "username";
    private static final Long BOOK_ID = 1L;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Delete Book Offer")
    void should_delete_book_offer() throws Exception {
        Principal principal = () -> USERNAME;

        mockMvc.perform(post("/book-offers/" + BOOK_ID + "/delete").principal(principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book-offers"));

        assertThat(
                jdbcTemplate.queryForObject("SELECT COUNT(*) FROM books WHERE id = ?", Integer.class, BOOK_ID), is(0));

        assertThat(
                jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM book_requesting_users WHERE book_id = ?", Integer.class, BOOK_ID),
                is(0));
    }
}
