package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileMapper extends JpaRepository<UserProfile, Long> {
    UserProfile findByUserId(Long userId);
}
