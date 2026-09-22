package com.toeicplatform.identity.application.port.in;

import com.toeicplatform.identity.domain.User;

import java.util.UUID;

public interface GetCurrentUserUseCase {
    User get(UUID userId);
}