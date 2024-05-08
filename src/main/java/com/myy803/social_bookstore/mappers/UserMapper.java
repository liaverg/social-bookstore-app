package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
