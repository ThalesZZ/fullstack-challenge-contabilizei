package com.thaleszz.challenge_contabilizei.models.invoice;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public enum Attachment {
    COMERCIO("0.06"),
    INDUSTRIA("0.085"),
    SERVICOS("0.11");

    private final String aliquot;

    public BigDecimal aliquot() {
        return new BigDecimal(this.aliquot);
    }
}
