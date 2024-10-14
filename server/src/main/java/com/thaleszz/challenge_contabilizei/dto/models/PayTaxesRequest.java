package com.thaleszz.challenge_contabilizei.dto.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record PayTaxesRequest(@NotNull @NotEmpty List<UUID> taxesIds) {
}
