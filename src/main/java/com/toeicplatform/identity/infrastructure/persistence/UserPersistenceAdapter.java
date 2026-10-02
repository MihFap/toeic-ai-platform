package com.toeicplatform.identity.infrastructure.persistence;

import com.toeicplatform.identity.application.port.out.UserRepository;
import com.toeicplatform.identity.domain.User;
import com.toeicplatform.identity.domain.*;

import java.util.Optional;
import java.util.UUID;

public class UserPersistenceAdapter implements UserRepository{

    private final SpringDataUserRepository repository;

    public UserPersistenceAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByEmail(String email) { return repository.existsByEmail(email);}

    @Override
    public Optional<User> findByEmail(String email) { return repository.findByEmail(email).map(this::toDomain); }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public User save(User user) {
        return toDomain(repository.save(toEntity(user)));
    }

    private UserJpaEntity toEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.id = user.id();
        entity.email = user.email();
        entity.passwordHash = user.passwordHash();
        entity.role = user.role().name();
        entity.tier = user.tier().name();
        entity.status = user.status().name();
        entity.createdAt = user.createdAt();
        entity.updatedAt = user.updatedAt();
        return entity;
    }

    private User toDomain(UserJpaEntity entity) {
        return new User(
                entity.id,
                entity.email,
                entity.passwordHash,
                UserRole.valueOf(entity.role),
                UserTier.valueOf(entity.tier),
                UserStatus.valueOf(entity.status),
                entity.createdAt,
                entity.updatedAt
        );
    }
}
