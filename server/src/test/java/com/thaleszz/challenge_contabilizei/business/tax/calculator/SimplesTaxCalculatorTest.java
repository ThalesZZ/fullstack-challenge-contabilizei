package com.thaleszz.challenge_contabilizei.business.tax.calculator;

import com.thaleszz.challenge_contabilizei.dto.models.CreateInvoiceRequest;
import com.thaleszz.challenge_contabilizei.models.invoice.Attachment;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class SimplesTaxCalculatorTest {

    private final SimplesTaxCalculator calculator = new SimplesTaxCalculator();

    @Test
    void calculate() {
        CreateInvoiceRequest data1 = new CreateInvoiceRequest(
                anyString(),
                Attachment.COMERCIO,
                anyString(),
                BigDecimal.valueOf(1000d),
                any());
        CreateInvoiceRequest data2 = new CreateInvoiceRequest(
                anyString(),
                Attachment.SERVICOS,
                anyString(),
                BigDecimal.valueOf(5000d),
                any());

        Invoice invoice1 = Invoice.fromRequest(data1);
        Invoice invoice2 = Invoice.fromRequest(data2);
        List<Invoice> invoices = Arrays.asList(invoice1, invoice2);

        Map<TaxType, BigDecimal> result = calculator.calculate(invoices);

        BigDecimal expected = new BigDecimal("610.00");

        assertEquals(1, result.size());
        assertTrue(result.containsKey(TaxType.SIMPLES_NACIONAL));
        assertEquals(0, result.get(TaxType.SIMPLES_NACIONAL).compareTo(expected));
    }
}