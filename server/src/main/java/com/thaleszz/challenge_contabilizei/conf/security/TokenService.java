package com.thaleszz.challenge_contabilizei.conf.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thaleszz.challenge_contabilizei.models.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenService {
    private final static String ISSUER = "contabilizei/auth-api";

    @Value("${api.security.token.secret}")
    private String secretKey;

    protected String generateToken(User user) {
        try {
            Algorithm algorithm = this.algorithm();
            Instant expirationDate = this.generateExpirationDate();
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token.", e);
        }
    }

    protected Optional<String> validateToken(String token) {
        try {
            Algorithm algorithm = this.algorithm();
            String username = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
            return Optional.of(username);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(this.secretKey);
    }

    private Instant generateExpirationDate() {
        ZoneOffset tzOffset = ZoneOffset.of("-03:00");
        return LocalDateTime.now()
                .plusHours(1)
                .toInstant(tzOffset);
    }
}
