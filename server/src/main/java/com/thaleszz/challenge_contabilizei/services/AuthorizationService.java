package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.user.RegisterDTO;
import com.thaleszz.challenge_contabilizei.models.user.UserModel;
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

    public UserModel register(@Valid RegisterDTO data) {
        UserDetails existingUser = this.repository.findByUsername(data.username());
        if (Objects.nonNull(existingUser)) throw new EntityExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel model = new UserModel(new RegisterDTO(data.username(), encryptedPassword, data.role()));

        return this.repository.save(model);
    }
}
