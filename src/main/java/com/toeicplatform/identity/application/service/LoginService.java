package com.toeicplatform.identity.application.service;

import com.toeicplatform.identity.application.port.in.LoginUseCase;
import com.toeicplatform.identity.application.port.out.PasswordHasher;
import com.toeicplatform.identity.application.port.out.TokenIssuer;
import com.toeicplatform.identity.application.port.out.UserRepository;
import com.toeicplatform.identity.domain.User;
import com.toeicplatform.identity.domain.UserStatus;
import com.toeicplatform.shared.exception.UnauthorizedException;


public final class LoginService implements LoginUseCase{
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TokenIssuer tokenIssuer;

    public LoginService(UserRepository userRepository, PasswordHasher passwordHasher, TokenIssuer tokenIssuer) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenIssuer = tokenIssuer;
    }

    @Override
    public Result login(Command command) {
        User user = userRepository.findByEmail(command.email().trim().toLowerCase())
                .orElseThrow(() -> invalidCredentials());

        if (user.status() != UserStatus.ACTIVE || !passwordHasher.matches(command.password(), user.passwordHash())) {
            throw invalidCredentials();
        }

        TokenIssuer.Token token = tokenIssuer.issue(user);
        return new Result(token.value(), token.expiresInseconds());
    }

    private UnauthorizedException invalidCredentials() {
        return new UnauthorizedException("AUTH_INVALID_CREDENTIALS", "Invalid email or password.");
    }
}
