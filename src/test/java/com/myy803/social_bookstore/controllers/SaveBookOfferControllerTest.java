package com.myy803.social_bookstore.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.myy803.social_bookstore.domain.formsdata.BookFormData;
import com.myy803.social_bookstore.domain.models.Role;
import java.security.Principal;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
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
public class SaveBookOfferControllerTest {

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
    @DisplayName("Save Book Offer")
    void should_save_book_offer() throws Exception {
        String username = "username";
        jdbcTemplate.update(
                "INSERT INTO users (username, password, role) VALUES (?,?, ?)",
                username,
                "encoded password",
                Role.USER.toString());
        jdbcTemplate.update("INSERT INTO user_profiles (id, user_id)  VALUES (?,?)", 1, 1);
        BookFormData formData = new BookFormData("Book Title", 2L, Arrays.asList(1L, 2L), "Summary");
        Principal principal = () -> username;

        mockMvc.perform(post("/book-offers/save").principal(principal).flashAttr("bookFormData", formData))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/homepage"));

        jdbcTemplate.query(
                "SELECT * FROM books WHERE title = ?",
                (rs, rowNum) -> {
                    Assertions.assertEquals("Book Title", rs.getString("title"));
                    Assertions.assertEquals("Summary", rs.getString("summary"));
                    return null;
                },
                "Book Title");
    }
}
