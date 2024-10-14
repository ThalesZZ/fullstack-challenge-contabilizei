package com.thaleszz.challenge_contabilizei.conf.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thaleszz.challenge_contabilizei.repositories.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);

        if (Objects.nonNull(token)) {
            String username = this.tokenService
                    .validateToken(token)
                    .orElseThrow(() -> new JWTVerificationException("Invalid token authentication."));

            UserDetails user = this.userRepository.findByUsername(username);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            WebAuthenticationDetails details = new WebAuthenticationDetailsSource().buildDetails(request);
            authentication.setDetails(details);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    @Nullable
    private String recoverToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (Objects.isNull(header)) return null;

        return header.replace("Bearer ", "");
    }

}
