package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.commands.RegisterCommand;

public interface RegisterUseCase {
    boolean register(RegisterCommand command);
}
