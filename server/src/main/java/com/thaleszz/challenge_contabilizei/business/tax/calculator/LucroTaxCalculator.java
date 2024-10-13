package com.thaleszz.challenge_contabilizei.business.tax.calculator;

import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LucroTaxCalculator implements TaxCalculationStrategy {
    @Override
    public Map<TaxType, BigDecimal> calculate(List<Invoice> invoices) {
        Map<TaxType, BigDecimal> result = new HashMap<>();
        TaxType[] taxTypes = new TaxType[]{TaxType.IRPJ, TaxType.ISS, TaxType.COFINS};

        for (Invoice invoice : invoices) {
            BigDecimal invoiceValue = invoice.getValue();
            for (TaxType taxType : taxTypes) {
                BigDecimal currentValue = result.getOrDefault(taxType, BigDecimal.ZERO);
                BigDecimal appliedTax = invoiceValue.multiply(taxType.aliquot());
                result.put(taxType, currentValue.add(appliedTax));
            }
        }

        return result;
    }
}
