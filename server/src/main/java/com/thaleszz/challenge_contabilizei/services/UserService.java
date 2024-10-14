package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.user.User;
import com.thaleszz.challenge_contabilizei.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByUsername(username);
    }

    public User register(User model) {
        UserDetails existingUser = this.repository.findByUsername(model.getUsername());
        if (Objects.nonNull(existingUser)) throw new EntityExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(model.getPassword());
        model.setPassword(encryptedPassword);

        return this.repository.save(model);
    }

    public Optional<User> get(@NotNull UUID id) {
        return this.repository.findById(id);
    }
}
