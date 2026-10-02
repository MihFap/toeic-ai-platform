package com.toeicplatform.identity.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity //cho JPA bt rằng class này là một thực thể được quản lý để phục vụ lưu trữ trong database
@Table(name = "users") //xac dinh bang ma entity anh xa den
class UserJpaEntity {
    @Id
    UUID id;

    @Column(nullable = false, unique = true, length = 320)
    String email;

    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @Column(nullable = false)
    String role;

    @Column(nullable = false)
    String tier;

    @Column(nullable = false)
    String status;

    @Column(name = "created_at", nullable = false)
    Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    Instant updatedAt;

    protected UserJpaEntity() {}
}
