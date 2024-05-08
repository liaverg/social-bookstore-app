package com.myy803.social_bookstore.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.myy803.social_bookstore.domain.commands.RegisterCommand;
import com.myy803.social_bookstore.domain.model.User;
import com.myy803.social_bookstore.mappers.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

    @InjectMocks
    private RegisterService registerService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("User Registered Successfully")
    void testRegisterSuccess() {
        RegisterCommand command = new RegisterCommand("user", "password123");
        when(userMapper.existsByUsername(command.username())).thenReturn(false);
        when(passwordEncoder.encode(command.password())).thenReturn("encodedPassword");

        boolean result = registerService.register(command);

        assertTrue(result);
        verify(userMapper, times(1)).existsByUsername("user");
        verify(userMapper, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("User Failed To Register")
    void testRegisterFailure_UserExists() {
        RegisterCommand command = new RegisterCommand("existingUser", "password123");
        when(userMapper.existsByUsername(command.username())).thenReturn(true);

        boolean result = registerService.register(command);

        assertFalse(result);
        verify(userMapper, times(1)).existsByUsername("existingUser");
        verify(userMapper, never()).save(any(User.class));
    }
}
