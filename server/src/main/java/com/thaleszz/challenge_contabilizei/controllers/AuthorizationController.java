package com.thaleszz.challenge_contabilizei.controllers;

import com.thaleszz.challenge_contabilizei.conf.security.AuthorizationService;
import com.thaleszz.challenge_contabilizei.dto.authentication.LoginRequest;
import com.thaleszz.challenge_contabilizei.dto.authentication.LoginResponse;
import com.thaleszz.challenge_contabilizei.dto.models.CreateUserRequest;
import com.thaleszz.challenge_contabilizei.models.user.User;
import com.thaleszz.challenge_contabilizei.services.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
@AllArgsConstructor
public class AuthorizationController {

    private final UserService userService;
    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest data) {
        String token = this.authorizationService.login(data.username(), data.password());
        LoginResponse response = new LoginResponse(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody @Valid CreateUserRequest data) {
        try {
            User model = new User(data);
            this.userService.register(model);
            String token = this.authorizationService.login(data.username(), data.password());
            LoginResponse response = new LoginResponse(token);
            return ResponseEntity.ok(response);
        } catch (EntityExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
