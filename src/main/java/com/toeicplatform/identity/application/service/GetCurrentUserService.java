package com.toeicplatform.identity.application.service;

import com.toeicplatform.identity.application.port.in.GetCurrentUserUseCase;
import com.toeicplatform.identity.application.port.out.UserRepository;
import com.toeicplatform.identity.domain.User;
import com.toeicplatform.shared.exception.NotFoundException;

import java.util.UUID;

public class GetCurrentUserService implements GetCurrentUserUseCase {
    private final UserRepository userRepository;
    public GetCurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->new NotFoundException("USER_NOT_FOUND", "User not found."));
    }
}
