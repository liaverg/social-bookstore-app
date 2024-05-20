package com.myy803.social_bookstore.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.security.Principal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
            "insert into book_requesting_users (book_id, user_profile_id, status) values (1, 1, 'PENDING'), (1, 1, 'APPROVED');"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ViewBookRequestsForSpecificBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private static final String USERNAME = "username";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("View Book Requests for Specific Book")
    public void should_view_book_requests_for_specific_book() throws Exception {
        Principal principal = () -> USERNAME;

        mockMvc.perform(get("/book-offers/1").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("books/view-book-offer-requests"))
                .andExpect(model().attributeExists("bookRequests"))
                .andExpect(model().attribute("bookRequests", hasSize(2)));
    }
}
