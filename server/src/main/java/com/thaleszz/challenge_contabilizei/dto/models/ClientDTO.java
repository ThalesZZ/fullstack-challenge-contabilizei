package com.thaleszz.challenge_contabilizei.dto.models;

import com.thaleszz.challenge_contabilizei.models.client.TaxRegime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientDTO(@NotBlank String companyName,
                        @NotBlank String cnpj,
                        @NotNull TaxRegime regime) {
}
