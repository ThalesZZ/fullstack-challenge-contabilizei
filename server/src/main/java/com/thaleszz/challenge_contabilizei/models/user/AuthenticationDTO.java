package com.thaleszz.challenge_contabilizei.models.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank String login,
                                @NotBlank String password) {
}
