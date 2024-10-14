package com.thaleszz.challenge_contabilizei.services.impl;

import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.repositories.InvoiceRepository;
import com.thaleszz.challenge_contabilizei.services.InvoiceService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository repository;

    @Override
    public Invoice create(@NotNull Client client, @NotNull Invoice model) {
        model.setClient(client);
        return this.repository.save(model);
    }

    @Override
    public List<Invoice> list() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Invoice> get(@NotNull UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(@NotNull UUID id) {
        this.repository.deleteById(id);
    }
}
