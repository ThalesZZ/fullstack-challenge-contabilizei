package com.thaleszz.challenge_contabilizei.business.tax.calculator;

import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TaxCalculationStrategy {
    Map<TaxType, BigDecimal> calculate(List<Invoice> invoices);
}
