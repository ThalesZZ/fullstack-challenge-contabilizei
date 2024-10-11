package com.thaleszz.challenge_contabilizei.models.invoice;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public enum Attachment {
    COMERCIO(.06),
    INDUSTRIA(.085),
    SERVICOS(.11);

    private final double aliquot;

    public BigDecimal aliquot() {
        return new BigDecimal(this.aliquot);
    }
}
