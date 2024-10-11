package com.thaleszz.challenge_contabilizei.models.tax;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public enum TaxType {
    SIMPLES_NACIONAL(null),
    IRPJ("0.048"),
    ISS("0.02"),
    COFINS("0.03");

    private final String aliquot;

    public BigDecimal aliquot() {
        if(this.equals(SIMPLES_NACIONAL))
            throw new UnsupportedOperationException("Aliquots of Simples Nacional taxes are calculated by their attachments.");
        return new BigDecimal(this.aliquot);
    }
}
