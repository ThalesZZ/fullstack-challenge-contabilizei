package com.thaleszz.challenge_contabilizei.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank String username,
                                @NotBlank String password) {
}
