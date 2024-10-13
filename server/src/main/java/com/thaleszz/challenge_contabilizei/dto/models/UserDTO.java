package com.thaleszz.challenge_contabilizei.dto.models;

import com.thaleszz.challenge_contabilizei.models.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotBlank String username,
                      @NotBlank String password,
                      @NotNull UserRole role) {
}
