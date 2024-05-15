package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.Author;
import com.myy803.social_bookstore.domain.models.Role;
import com.myy803.social_bookstore.domain.models.User;
import com.myy803.social_bookstore.domain.models.UserProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserProfileMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Test
    @DisplayName("Find User Profile by User ID")
    public void should_find_user_profile_by_user_id() {
        User user = new User("testUser", "password", Role.USER);
        userMapper.save(user);

        UserProfile foundProfile = userProfileMapper.findByUserId(user.getId());

        Assertions.assertNotNull(foundProfile);
        Assertions.assertEquals(user.getId(), foundProfile.getUser().getId());
    }

    @Test
    @DisplayName("Fail to Find User Profile by User ID")
    public void should_fail_to_find_user_profile_by_user_id() {
        Long nonExistingUserId = 1L;

        UserProfile foundProfile = userProfileMapper.findByUserId(nonExistingUserId);

        Assertions.assertNull(foundProfile);
    }
}
