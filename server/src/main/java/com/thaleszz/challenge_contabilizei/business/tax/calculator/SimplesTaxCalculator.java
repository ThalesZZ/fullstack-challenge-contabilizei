package com.thaleszz.challenge_contabilizei.business.tax.calculator;

import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SimplesTaxCalculator implements TaxCalculationStrategy {

    @Override
    public Map<TaxType, BigDecimal> calculate(List<Invoice> invoices) {
        BigDecimal totalTaxValue = BigDecimal.ZERO;

        for (Invoice invoice : invoices) {
            BigDecimal invoiceTaxValue = invoice.getValue().multiply(invoice.getAttachment().aliquot());
            totalTaxValue = totalTaxValue.add(invoiceTaxValue);
        }

        return Collections.singletonMap(TaxType.SIMPLES_NACIONAL, totalTaxValue);
    }
}
