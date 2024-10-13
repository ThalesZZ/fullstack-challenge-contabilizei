package com.thaleszz.challenge_contabilizei.business.tax.calculator;

import com.thaleszz.challenge_contabilizei.models.invoice.Attachment;
import com.thaleszz.challenge_contabilizei.dto.models.InvoiceDTO;
import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceModel;
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
        InvoiceDTO data1 = new InvoiceDTO(
                anyString(),
                Attachment.COMERCIO,
                anyString(),
                BigDecimal.valueOf(1000d),
                any());
        InvoiceDTO data2 = new InvoiceDTO(
                anyString(),
                Attachment.SERVICOS,
                anyString(),
                BigDecimal.valueOf(500d),
                any());

        InvoiceModel invoice1 = new InvoiceModel(data1);
        InvoiceModel invoice2 = new InvoiceModel(data2);
        List<InvoiceModel> invoices = Arrays.asList(invoice1, invoice2);

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