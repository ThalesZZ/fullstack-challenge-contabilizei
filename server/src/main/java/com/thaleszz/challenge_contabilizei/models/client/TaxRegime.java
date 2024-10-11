package com.thaleszz.challenge_contabilizei.models.client;

import com.thaleszz.challenge_contabilizei.business.LucroTaxCalculator;
import com.thaleszz.challenge_contabilizei.business.SimplesTaxCalculator;
import com.thaleszz.challenge_contabilizei.business.TaxCalculationStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaxRegime {
    SIMPLES_NACIONAL(new SimplesTaxCalculator()),
    LUCRO_PRESUMIDO(new LucroTaxCalculator());

    private final TaxCalculationStrategy calculator;

    public TaxCalculationStrategy calculator() {
        return this.calculator;
    }
}
