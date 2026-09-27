package com.toeicplatform.identity.application.port.out;

import com.toeicplatform.identity.domain.User;

public interface TokenIssuer {
    Token issue(User user);

    record Token(String value,
                 long expiresInseconds
    ) {}
}
