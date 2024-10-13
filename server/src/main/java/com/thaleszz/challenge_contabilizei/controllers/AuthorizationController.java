package com.thaleszz.challenge_contabilizei.controllers;

import com.thaleszz.challenge_contabilizei.models.user.AuthenticationDTO;
import com.thaleszz.challenge_contabilizei.models.user.RegisterDTO;
import com.thaleszz.challenge_contabilizei.models.user.UserModel;
import com.thaleszz.challenge_contabilizei.services.AuthorizationService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
@AllArgsConstructor
public class AuthorizationController {

    private final AuthorizationService service;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationDTO data) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        data.login(),
                        data.password());
        this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO data) {
        try {
            UserModel user = this.service.register(data);
            return ResponseEntity.noContent().build();
        } catch (EntityExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
