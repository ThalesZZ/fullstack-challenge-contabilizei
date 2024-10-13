package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.dto.models.InvoiceDTO;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.repositories.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceService {
    private final ClientService clientService;
    private final InvoiceRepository repository;

    public Invoice create(@Valid InvoiceDTO data) {
        Client client = this.clientService.get(data.clientId())
                .orElseThrow(EntityNotFoundException::new);

        Invoice model = new Invoice(data);
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
        Invoice model = this.get(id)
                .orElseThrow(EntityNotFoundException::new);
        this.repository.delete(model);
    }
}
