package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.repositories.InvoiceRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository repository;

    public Invoice create(@NotNull Client client, @NotNull Invoice model) {
        model.setClient(client);
        return this.repository.save(model);
    }

    public List<Invoice> list() {
        return this.repository.findAll();
    }

    public Optional<Invoice> get(@NotNull UUID id) {
        return this.repository.findById(id);
    }

    public void delete(@NotNull UUID id) {
        this.repository.deleteById(id);
    }
}
