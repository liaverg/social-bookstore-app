package com.myy803.social_bookstore.controllers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.myy803.social_bookstore.domain.formsdata.UserProfileFormData;
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
@Sql(scripts = "classpath:add-book-categories.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:add-authors.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ViewUserProfileControllerTest {

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
    @DisplayName("View User Profile")
    public void should_view_user_profile() throws Exception {
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
        jdbcTemplate.update(
                "INSERT INTO user_profile_favorite_book_categories (id, user_profile_id, book_category_id) VALUES (?,?, ?)",
                1,
                1,
                10);
        jdbcTemplate.update(
                "INSERT INTO user_profile_favorite_authors (id, user_profile_id, author_id) VALUES (?, ?, ?)", 1, 1, 2);
        Principal principal = () -> username;

        mockMvc.perform(get("/profile").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attribute("allBookCategories", hasSize(11)))
                .andExpect(model().attribute("allAuthors", hasSize(2)))
                .andExpect(model().attribute("userProfileFormData", instanceOf(UserProfileFormData.class)));
    }
}
