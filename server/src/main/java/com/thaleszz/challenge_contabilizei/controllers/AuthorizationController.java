package com.thaleszz.challenge_contabilizei.controllers;

import com.thaleszz.challenge_contabilizei.conf.security.TokenService;
import com.thaleszz.challenge_contabilizei.dto.models.UserDTO;
import com.thaleszz.challenge_contabilizei.dto.requests.AuthenticationDTO;
import com.thaleszz.challenge_contabilizei.dto.responses.LoginResponseDTO;
import com.thaleszz.challenge_contabilizei.models.user.User;
import com.thaleszz.challenge_contabilizei.services.AuthorizationService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
@AllArgsConstructor
public class AuthorizationController {

    private final AuthorizationService service;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        String token = this.doLogin(data);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody UserDTO data) {
        try {
            this.service.register(data);
            String token = this.doLogin(new AuthenticationDTO(data.username(), data.password()));
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (EntityExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private String doLogin(AuthenticationDTO data) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        data.username(),
                        data.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        return this.tokenService.generateToken((User) auth.getPrincipal());
    }

}
