package com.myy803.social_bookstore.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.myy803.social_bookstore.domain.models.Role;
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
        scripts = {"classpath:add-book-categories.sql", "classpath:add-authors.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ViewBookOffersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("View Book Offers")
    public void should_view_book_offers() throws Exception {
        String username = "username";
        String bookTitle1 = "Book Title 1";
        String bookTitle2 = "Book Title 2";
        String summary1 = "Summary of Book 1";
        String summary2 = "Summary of Book 2";
        jdbcTemplate.update(
                "INSERT INTO users (username, password, role) VALUES (?,?, ?)",
                username,
                "encoded password",
                Role.USER.toString());
        jdbcTemplate.update(
                "INSERT INTO user_profiles (id, user_id, full_name, address, age, phone_number)  VALUES (?,?,?,?,?,?)",
                1,
                1,
                "John Doe",
                "123 Main St",
                "30",
                "123-456-7890");
        jdbcTemplate.update(
                "INSERT INTO books (id, user_profile_id, title, book_category_id, summary) VALUES (?, ?, ?, ?, ?)",
                1,
                1,
                bookTitle1,
                1,
                summary1);
        jdbcTemplate.update(
                "INSERT INTO books (id, user_profile_id, title, book_category_id, summary) VALUES (?, ?, ?, ?, ?)",
                2,
                1,
                bookTitle2,
                2,
                summary2);
        Principal principal = () -> username;

        mockMvc.perform(get("/book-offers").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("books/view-book-offers"))
                .andExpect(model().attribute("allBookOffers", hasSize(2)));
    }

    @Test
    @DisplayName("View Book Offers - Empty Offers")
    public void should_view_empty_book_offers() throws Exception {
        String username = "username";
        jdbcTemplate.update(
                "INSERT INTO users (username, password, role) VALUES (?,?, ?)",
                username,
                "encoded password",
                Role.USER.toString());
        jdbcTemplate.update(
                "INSERT INTO user_profiles (id, user_id, full_name, address, age, phone_number)  VALUES (?,?,?,?,?,?)",
                1,
                1,
                "John Doe",
                "123 Main St",
                "30",
                "123-456-7890");
        Principal principal = () -> username;

        mockMvc.perform(get("/book-offers").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("books/view-book-offers"))
                .andExpect(model().attribute("allBookOffers", hasSize(0)));
    }
}
