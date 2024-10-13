package com.thaleszz.challenge_contabilizei.models.user;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(@NotBlank String token) {
}
