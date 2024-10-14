package com.thaleszz.challenge_contabilizei.services.impl;

import com.thaleszz.challenge_contabilizei.models.user.User;
import com.thaleszz.challenge_contabilizei.repositories.UserRepository;
import com.thaleszz.challenge_contabilizei.services.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByUsername(username);
    }

    @Override
    public User register(User model) {
        UserDetails existingUser = this.repository.findByUsername(model.getUsername());
        if (Objects.nonNull(existingUser)) throw new EntityExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(model.getPassword());
        model.setPassword(encryptedPassword);

        return this.repository.save(model);
    }

    @Override
    public Optional<User> get(@NotNull UUID id) {
        return this.repository.findById(id);
    }
}
