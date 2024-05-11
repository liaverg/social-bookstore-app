package com.myy803.social_bookstore.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.myy803.social_bookstore.domain.formsdata.RegisterFormData;
import com.myy803.social_bookstore.domain.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:users/clean-users.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //    @Mock
    //    private RegisterUseCase registerUseCase;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Get Registration Page")
    void registerFormPageShouldReturnCorrectViewAndModel() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/register"))
                .andExpect(model().attributeExists("registerFormData"));
    }

    @Test
    void registerProcessShouldRedirectToLoginPageWhenRegistrationSucceeds() throws Exception {
        RegisterFormData formData = new RegisterFormData();
        formData.setUsername("username");
        formData.setPassword("password");

        mockMvc.perform(post("/register").flashAttr("registerFormData", formData))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        jdbcTemplate.query(
                "SELECT * FROM users WHERE username = ?",
                (rs, rowNum) -> {
                    Assertions.assertEquals(1, rs.getInt("id"));
                    Assertions.assertEquals(formData.getUsername(), rs.getString("username"));
                    passwordEncoder.matches(formData.getPassword(), rs.getString("password"));
                    Assertions.assertEquals(Role.USER.toString(), rs.getString("role"));
                    return null;
                },
                formData.getUsername());
    }

    @Test
    public void testRegisterFailure() throws Exception {
        RegisterFormData formData = new RegisterFormData("username", "password");
        jdbcTemplate.update(
                "INSERT INTO users (username, password, role) VALUES (?,?, ?)",
                formData.getUsername(),
                passwordEncoder.encode(formData.getPassword()),
                Role.USER.toString());

        MvcResult mvcResult = mockMvc.perform(post("/register").flashAttr("registerFormData", formData))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/register"))
                .andReturn();

        ModelAndView mav = mvcResult.getModelAndView();
        assertThat(mav.getModel().get("message")).isEqualTo("User Failed To Register!");
    }
}
