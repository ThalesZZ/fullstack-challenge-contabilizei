package com.thaleszz.challenge_contabilizei.dto.responses;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(@NotBlank String token) {
}
