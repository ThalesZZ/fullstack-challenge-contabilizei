package com.thaleszz.challenge_contabilizei.dto.models;

import com.thaleszz.challenge_contabilizei.models.client.TaxRegime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateClientRequest(@NotBlank String companyName,
                                  @NotBlank String cnpj,
                                  @NotBlank String email,
                                  @NotNull TaxRegime regime,
                                  @NotNull UUID userId) {
}
