package com.thaleszz.challenge_contabilizei.dto.models;

import com.thaleszz.challenge_contabilizei.models.invoice.Attachment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvoiceResponse(String number,
                              Attachment attachment,
                              LocalDateTime emissionDate,
                              BigDecimal value) {
}
