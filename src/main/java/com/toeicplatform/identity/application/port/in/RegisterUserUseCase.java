package com.toeicplatform.identity.application.port.in;

import com.toeicplatform.identity.domain.User;

public interface RegisterUserUseCase {
    User register(Command command);
    record Command(String email, String password) {}
}