package com.thaleszz.challenge_contabilizei.conf.security;

import com.thaleszz.challenge_contabilizei.models.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationService {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public String login(String username, String password) {
        new TokenService();
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        return this.tokenService.generateToken((User) auth.getPrincipal());
    }

}
