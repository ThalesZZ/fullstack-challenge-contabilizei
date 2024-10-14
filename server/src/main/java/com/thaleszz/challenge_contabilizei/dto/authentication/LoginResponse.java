package com.thaleszz.challenge_contabilizei.dto.authentication;

import jakarta.validation.constraints.NotBlank;

public record LoginResponse(@NotBlank String token) {
}
