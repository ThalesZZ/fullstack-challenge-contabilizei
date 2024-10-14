package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceService {
    Invoice create(@NotNull Client client, @NotNull Invoice model);

    List<Invoice> list();

    Optional<Invoice> get(@NotNull UUID id);

    void delete(@NotNull UUID id);
}
