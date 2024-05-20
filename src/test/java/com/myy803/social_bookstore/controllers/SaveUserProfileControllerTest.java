package com.myy803.social_bookstore.controllers;

import static com.myy803.social_bookstore.config.EndpointConfig.USER_PROFILE_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.myy803.social_bookstore.domain.formsdata.UserProfileFormData;
import java.security.Principal;
import java.util.List;
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
public class SaveUserProfileControllerTest {

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
    @DisplayName("Save User Profile")
    void should_save_user_profile() throws Exception {
        UserProfileFormData formData =
                new UserProfileFormData("John Doe", "123 Main St", "30", "123-456-7890", List.of(10L), List.of(2L));
        Principal principal = () -> USERNAME;

        mockMvc.perform(post(USER_PROFILE_PATH).principal(principal).flashAttr("userProfileFormData", formData))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(USER_PROFILE_PATH));

        jdbcTemplate.query(
                "SELECT up.*, bc.category AS book_category, a.name AS author " + "FROM user_profiles up "
                        + "INNER JOIN user_profile_favorite_book_categories upfbc ON up.id = upfbc.user_profile_id "
                        + "INNER JOIN book_categories bc ON upfbc.book_category_id = bc.id "
                        + "INNER JOIN user_profile_favorite_authors upfa ON up.id = upfa.user_profile_id "
                        + "INNER JOIN authors a ON upfa.author_id = a.id "
                        + "WHERE up.user_id = (SELECT id FROM users WHERE username = ?)",
                (rs, rowNum) -> {
                    Assertions.assertEquals(formData.getFullName(), rs.getString("full_name"));
                    Assertions.assertEquals(formData.getAddress(), rs.getString("address"));
                    Assertions.assertEquals(formData.getAge(), rs.getString("age"));
                    Assertions.assertEquals(formData.getPhoneNumber(), rs.getString("phone_number"));
                    Assertions.assertEquals("Crime", rs.getString("book_category"));
                    Assertions.assertEquals("Agatha Cristie", rs.getString("author"));
                    return null;
                },
                USERNAME);
    }
}
