package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.business.tax.calculator.TaxCalculationStrategy;
import com.thaleszz.challenge_contabilizei.business.tax.due_date.TaxDueDateCalculator;
import com.thaleszz.challenge_contabilizei.business.tax.due_date.TaxDueDateStrategy;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.models.tax.Tax;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;
import com.thaleszz.challenge_contabilizei.repositories.TaxRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaxService {

    private final ClientService clientService;
    private final TaxRepository repository;

    public List<Tax> requestCalculation(@NotNull UUID clientId, @NotNull YearMonth referenceDate) {
        Client client = this.clientService.get(clientId).orElseThrow(EntityExistsException::new);

        List<Invoice> filteredInvoices = client
                .getInvoices().stream()
                .filter(invoice -> referenceDate.getMonth() == invoice.getEmissionDate().getMonth()
                        && referenceDate.getYear() == invoice.getEmissionDate().getYear())
                .toList();

        TaxCalculationStrategy taxValueCalculator = client.getRegime().calculator();
        TaxDueDateStrategy dueDateCalculator = new TaxDueDateCalculator();

        Map<TaxType, BigDecimal> taxesByType = taxValueCalculator.calculate(filteredInvoices);
        LocalDateTime dueDate = dueDateCalculator.calculate(referenceDate);

        List<Tax> taxes = taxesByType
                .entrySet()
                .stream()
                .map(entry ->
                        new Tax(
                                null,
                                entry.getKey(),
                                dueDate,
                                referenceDate,
                                entry.getValue(),
                                false,
                                client))
                .toList();

        return this.repository.saveAll(taxes);
    }

}
