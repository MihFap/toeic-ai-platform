package com.toeicplatform.identity.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class User {
    private final UUID id;
    private final String email;
    private final String passwordHash;
    private final UserRole role;
    private final UserTier tier;
    private final UserStatus status;
    private final Instant createdAt;
    private final Instant updatedAt;

    public User(UUID id, String email, String passwordHash, UserRole role, UserTier tier,
                UserStatus status, Instant createdAt, Instant updatedAt) {
        this.id = Objects.requireNonNull(id);
        this.email = normalizeEmail(email);
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.role = Objects.requireNonNull(role);
        this.tier = Objects.requireNonNull(tier);
        this.status = Objects.requireNonNull(status);
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
    }

    public static User registerStudent(UUID id, String email, String passwordHash, Instant now) {
        return new User(id, email, passwordHash, UserRole.STUDENT, UserTier.FREE, UserStatus.ACTIVE, now, now);
    }

    private static String normalizeEmail(String email) {
        return Objects.requireNonNull(email).trim().toLowerCase();
    }

    public UUID id() { return id; }
    public String email() { return email; }
    public String passwordHash() { return passwordHash; }
    public UserRole role() { return role; }
    public UserTier tier() { return tier; }
    public UserStatus status() { return status; }
    public Instant createdAt() { return createdAt; }
    public Instant updatedAt() { return updatedAt; }
}
