package com.toeicplatform.identity.application.service;


import com.toeicplatform.identity.application.port.in.RegisterUserUseCase;
import com.toeicplatform.identity.application.port.out.PasswordHasher;
import com.toeicplatform.identity.application.port.out.UserRepository;
import com.toeicplatform.identity.domain.User;
import com.toeicplatform.shared.exception.ConflictException;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

public final class RegisterUserService implements RegisterUserUseCase {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final Clock clock;

    public RegisterUserService(UserRepository userRepository, PasswordHasher passwordHasher, Clock clock) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.clock = clock;
    }

    @Override
    public User register(Command command) {
        String email = command.email().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("EMAIL_ALREADY_EXISTS", "An account with this email already exists.");
        }

        Instant now = clock.instant();
        User user = User.registerStudent(UUID.randomUUID(), email, passwordHasher.hash(command.password()), now);
        return userRepository.save(user);
    }

}