package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.commands.RegisterCommand;
import com.myy803.social_bookstore.domain.models.Role;
import com.myy803.social_bookstore.domain.models.User;
import com.myy803.social_bookstore.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public boolean register(RegisterCommand command) {
        if (userMapper.existsByUsername(command.username())) {
            return false;
        }
        String encodedPassword = passwordEncoder.encode(command.password());
        User user = new User(command.username(), encodedPassword, Role.USER);
        userMapper.save(user);
        return true;
    }
}
