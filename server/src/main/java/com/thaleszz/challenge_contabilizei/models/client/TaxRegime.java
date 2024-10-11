package com.thaleszz.challenge_contabilizei.models.client;

import com.thaleszz.challenge_contabilizei.business.tax.calculator.LucroTaxCalculator;
import com.thaleszz.challenge_contabilizei.business.tax.calculator.SimplesTaxCalculator;
import com.thaleszz.challenge_contabilizei.business.tax.calculator.TaxCalculationStrategy;
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
