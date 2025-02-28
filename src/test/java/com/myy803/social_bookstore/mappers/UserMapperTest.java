package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.Role;
import com.myy803.social_bookstore.domain.models.User;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("Find User Successfully")
    public void should_find_user_when_user_exists() {
        String username = "testUser";
        User user = new User(username, "password", Role.USER);
        userMapper.save(user);

        Optional<User> foundUser = userMapper.findByUsername(username);

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(username, foundUser.get().getUsername());
    }

    @Test
    @DisplayName("Failed to Find User")
    public void should_return_null_when_user_does_not_exist() {
        String username = "nonExistingUser";

        Optional<User> foundUser = userMapper.findByUsername(username);

        Assertions.assertTrue(foundUser.isEmpty());
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
