package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.model.Role;
import com.myy803.social_bookstore.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "classpath:users/clean-users.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("Find User Successfully")
    public void should_find_user_when_user_exists() {
        String username = "testUser";
        User user = new User(username, "password", Role.USER);
        userMapper.save(user);

        User foundUser = userMapper.findByUsername(username);

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(username, foundUser.getUsername());
    }

    @Test
    @DisplayName("Failed to Find User")
    public void should_return_null_when_user_does_not_exist() {
        String username = "nonExistingUser";

        User foundUser = userMapper.findByUsername(username);

        Assertions.assertNull(foundUser);
    }

    @Test
    @DisplayName("User Exists")
    public void should_return_true_when_user_exists() {
        String username = "existingUser";
        User user = new User(username, "password", Role.USER);
        userMapper.save(user);

        boolean exists = userMapper.existsByUsername(username);

        Assertions.assertTrue(exists);
    }

    @Test
    @DisplayName("User Doesn't Exist")
    public void should_return_false_when_user_does_not_exist() {
        String username = "nonExistingUser";

        boolean exists = userMapper.existsByUsername(username);

        Assertions.assertFalse(exists);
    }
}
