package com.thaleszz.challenge_contabilizei.models.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientDTO(@NotBlank String companyName,
                        @NotBlank String cnpj,
                        @NotNull TaxRegime regime) {
}
