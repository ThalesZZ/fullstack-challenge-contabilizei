package com.thaleszz.challenge_contabilizei.business;

import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceModel;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LucroTaxCalculator implements TaxCalculationStrategy {
    @Override
    public Map<TaxType, BigDecimal> calculate(List<InvoiceModel> invoices) {
        BigDecimal totalTaxValue = BigDecimal.ZERO;

        for (InvoiceModel invoice : invoices) {
            BigDecimal invoiceTaxValue = invoice.getValue().multiply(invoice.getAttachment().aliquot());
            totalTaxValue = totalTaxValue.add(invoiceTaxValue);
        }

        return Collections.singletonMap(TaxType.SIMPLES_NACIONAL, totalTaxValue);
    }
}
