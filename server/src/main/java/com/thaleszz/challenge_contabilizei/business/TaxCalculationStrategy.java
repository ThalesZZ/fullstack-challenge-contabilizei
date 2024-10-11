package com.thaleszz.challenge_contabilizei.business;

import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceModel;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TaxCalculationStrategy {
    Map<TaxType, BigDecimal> calculate(List<InvoiceModel> invoices);
}
