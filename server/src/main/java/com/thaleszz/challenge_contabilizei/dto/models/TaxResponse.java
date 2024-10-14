package com.thaleszz.challenge_contabilizei.dto.models;

import com.thaleszz.challenge_contabilizei.models.tax.TaxType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

public record TaxResponse(TaxType type,
                          LocalDateTime dueDate,
                          YearMonth referenceDate,
                          BigDecimal value,
                          boolean paid) {
}
