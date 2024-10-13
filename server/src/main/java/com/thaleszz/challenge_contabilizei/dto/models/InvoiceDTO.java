package com.thaleszz.challenge_contabilizei.dto.models;

import com.thaleszz.challenge_contabilizei.models.invoice.Attachment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record InvoiceDTO(@NotBlank String number,
                         @NotNull Attachment attachment,
                         String description,
                         @NotNull @Positive BigDecimal value,
                         @NotNull UUID clientId) {
}
