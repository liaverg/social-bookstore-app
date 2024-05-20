package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.BOOK_OFFERS_SAVE_PATH;
import static com.myy803.social_bookstore.config.EndpointConfig.HOMEPAGE_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.myy803.social_bookstore.domain.formsdata.BookFormData;
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
        statements = {
            "insert into users (username, password, role) values ('username', 'encodedPassword', 'USER');",
            "insert into user_profiles (user_id) values (1);"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SaveBookOfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String USERNAME = "username";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Save Book Offer")
    void should_save_book_offer() throws Exception {
        BookFormData formData = new BookFormData("Book Title", 2L, Arrays.asList(1L, 2L), "Summary");
        Principal principal = () -> USERNAME;

        mockMvc.perform(post(BOOK_OFFERS_SAVE_PATH).principal(principal).flashAttr("bookFormData", formData))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(HOMEPAGE_PATH));

        jdbcTemplate.query(
                "SELECT * FROM books WHERE title = ?",
                (rs, rowNum) -> {
                    Assertions.assertEquals(formData.getBookTitle(), rs.getString("title"));
                    Assertions.assertEquals(formData.getSummary(), rs.getString("summary"));
                    return null;
                },
                "Book Title");
    }
}
