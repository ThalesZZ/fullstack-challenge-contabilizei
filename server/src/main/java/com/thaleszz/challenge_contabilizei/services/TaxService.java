package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.business.TaxCalculationStrategy;
import com.thaleszz.challenge_contabilizei.models.client.ClientModel;
import com.thaleszz.challenge_contabilizei.models.client.TaxRegime;
import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceModel;
import com.thaleszz.challenge_contabilizei.models.tax.TaxModel;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaxService {

    private final ClientService clientService;

    public void requestCalculation(UUID clientId, YearMonth referenceDate) {
        ClientModel client = this.clientService.get(clientId).orElseThrow(EntityExistsException::new);
        TaxRegime regime = client.getRegime();

        List<InvoiceModel> filteredInvoices = client
                .getInvoices().stream()
                .filter(invoice -> referenceDate.getMonth() == invoice.getEmissionDate().getMonth()
                        && referenceDate.getYear() == invoice.getEmissionDate().getYear())
                .toList();

        TaxCalculationStrategy taxCalculator = client.getRegime().calculator();
        Map<TaxType, BigDecimal> taxes = taxCalculator.calculate(filteredInvoices);
    }

    @Transactional
    private List<TaxModel> registerTaxes() {
        return null;
    }
}
