package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.dto.models.UserDTO;
import com.thaleszz.challenge_contabilizei.models.user.User;
import com.thaleszz.challenge_contabilizei.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByUsername(username);
    }

    public User register(@Valid UserDTO data) {
        UserDetails existingUser = this.repository.findByUsername(data.username());
        if (Objects.nonNull(existingUser)) throw new EntityExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User model = new User(new UserDTO(data.username(), encryptedPassword, data.role()));

        return this.repository.save(model);
    }
}
