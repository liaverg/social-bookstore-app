package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.model.Role;
import com.myy803.social_bookstore.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "classpath:users/clean-users.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("Find User Successfully")
    public void findByUsername_WhenUserExists_ShouldReturnUser() {
        String username = "testUser";
        User user = new User(username, "password", Role.USER);
        userMapper.save(user);

        User foundUser = userMapper.findByUsername(username);

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(username, foundUser.getUsername());
    }

    @Test
    @DisplayName("Failed to Find User")
    public void findByUsername_WhenUserDoesNotExist_ShouldReturnNull() {
        String username = "nonExistingUser";

        User foundUser = userMapper.findByUsername(username);

        Assertions.assertNull(foundUser);
    }

    @Test
    @DisplayName("User Exists")
    public void existsByUsername_WhenUserExists_ShouldReturnTrue() {
        String username = "existingUser";
        User user = new User(username, "password", Role.USER);
        userMapper.save(user);

        boolean exists = userMapper.existsByUsername(username);

        Assertions.assertTrue(exists);
    }

    @Test
    @DisplayName("User Doesn't Exist")
    public void existsByUsername_WhenUserDoesNotExist_ShouldReturnFalse() {
        String username = "nonExistingUser";

        boolean exists = userMapper.existsByUsername(username);

        Assertions.assertFalse(exists);
    }
}
