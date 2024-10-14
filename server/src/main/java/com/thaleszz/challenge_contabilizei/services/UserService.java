package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.user.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    User register(User model);

    Optional<User> get(@NotNull UUID id);
}
