package com.thaleszz.challenge_contabilizei.dto.models;

import jakarta.validation.constraints.NotNull;

import java.time.YearMonth;
import java.util.UUID;

public record CalculateTaxRequest(@NotNull UUID clientId,
                                  @NotNull YearMonth referenceDate) {
}
