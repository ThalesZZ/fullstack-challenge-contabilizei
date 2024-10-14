package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.tax.Tax;
import jakarta.validation.constraints.NotNull;

import java.time.YearMonth;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface TaxService {
    List<Tax> requestCalculation(@NotNull UUID clientId, @NotNull YearMonth referenceDate);

    List<Tax> pay(Collection<UUID> ids);

    List<Tax> getClientTaxes(UUID clientId);
}
