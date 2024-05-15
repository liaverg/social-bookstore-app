package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserMapper extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT id FROM User WHERE username = ?1")
    Long findIdByUsername(String username);

    boolean existsByUsername(String username);
}
