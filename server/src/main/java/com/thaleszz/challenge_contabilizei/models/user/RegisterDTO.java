package com.thaleszz.challenge_contabilizei.models.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotBlank String username,
                          @NotBlank String password,
                          @NotNull UserRole role) {
}
