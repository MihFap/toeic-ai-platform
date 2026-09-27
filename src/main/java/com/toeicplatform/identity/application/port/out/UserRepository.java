package com.toeicplatform.identity.application.port.out;

import com.toeicplatform.identity.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);

    User save(User user);
}
