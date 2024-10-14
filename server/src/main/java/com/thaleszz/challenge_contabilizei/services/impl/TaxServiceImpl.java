package com.thaleszz.challenge_contabilizei.services.impl;

import com.thaleszz.challenge_contabilizei.business.tax.calculator.TaxCalculationStrategy;
import com.thaleszz.challenge_contabilizei.business.tax.due_date.TaxDueDateCalculator;
import com.thaleszz.challenge_contabilizei.business.tax.due_date.TaxDueDateStrategy;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.models.tax.Tax;
import com.thaleszz.challenge_contabilizei.models.tax.TaxType;
import com.thaleszz.challenge_contabilizei.repositories.TaxRepository;
import com.thaleszz.challenge_contabilizei.services.ClientService;
import com.thaleszz.challenge_contabilizei.services.TaxService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaxServiceImpl implements TaxService {

    private final ClientService clientService;
    private final TaxRepository repository;

    @Override
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
                .map(entry -> {
                    TaxType taxType = entry.getKey();
                    BigDecimal value = entry.getValue();
                    return new Tax(taxType, dueDate, referenceDate, value, client);
                })
                .toList();

        return this.repository.saveAll(taxes);
    }

    @Override
    public List<Tax> pay(Collection<UUID> ids) {
        List<Tax> taxes = this.repository.findAllById(ids);
        if (taxes.size() != ids.size()) throw new EntityNotFoundException();
        taxes = taxes.stream().peek(tax -> tax.setPaid(true)).toList();
        return this.repository.saveAll(taxes);
    }

    @Override
    public List<Tax> getClientTaxes(UUID clientId) {
        return this.repository.findByClientId(clientId);
    }
}
