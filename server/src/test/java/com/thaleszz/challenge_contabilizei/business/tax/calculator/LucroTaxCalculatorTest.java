package com.thaleszz.challenge_contabilizei.business.tax.calculator;

import com.thaleszz.challenge_contabilizei.dto.models.CreateInvoiceRequest;
import com.thaleszz.challenge_contabilizei.models.invoice.Attachment;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class LucroTaxCalculatorTest {

    private final LucroTaxCalculator calculator = new LucroTaxCalculator();

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
                BigDecimal.valueOf(500d),
                any());

        Invoice invoice1 = Invoice.fromRequest(data1);
        Invoice invoice2 = Invoice.fromRequest(data2);
        List<Invoice> invoices = Arrays.asList(invoice1, invoice2);

        Map<TaxType, BigDecimal> actual = calculator.calculate(invoices);

        Map<TaxType, BigDecimal> expected = new HashMap<>() {{
            put(TaxType.IRPJ, new BigDecimal("72.00"));
            put(TaxType.ISS, new BigDecimal("30.00"));
            put(TaxType.COFINS, new BigDecimal("45.00"));
        }};

        assertEquals(expected.size(), actual.size());
        for (Map.Entry<TaxType, BigDecimal> entry : expected.entrySet()) {
            TaxType taxType = entry.getKey();
            assertTrue(actual.containsKey(taxType));
            assertEquals(0, actual.get(taxType).compareTo(entry.getValue()));
        }
    }
}