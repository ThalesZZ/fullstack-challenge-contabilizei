package com.thaleszz.challenge_contabilizei.conf.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thaleszz.challenge_contabilizei.models.user.UserModel;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private final static String ISSUER = "contabilizei/auth-api";

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String generateToken(UserModel user) {
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

    @Nullable
    public String validateToken(String token) {
        try {
            Algorithm algorithm = this.algorithm();
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
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
