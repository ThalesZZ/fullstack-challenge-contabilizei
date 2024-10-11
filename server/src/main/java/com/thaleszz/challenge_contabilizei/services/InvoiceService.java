package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.client.ClientModel;
import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceDTO;
import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceModel;
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

    public InvoiceModel create(@Valid InvoiceDTO data) {
        ClientModel client = this.clientService.get(data.clientId())
                .orElseThrow(EntityNotFoundException::new);

        InvoiceModel model = new InvoiceModel(data);
        model.setClient(client);

        return this.repository.save(model);
    }

    public List<InvoiceModel> list() {
        return this.repository.findAll();
    }

    public Optional<InvoiceModel> get(@NotNull UUID id) {
        return this.repository.findById(id);
    }

    public void delete(@NotNull UUID id) {
        InvoiceModel model = this.get(id)
                .orElseThrow(EntityNotFoundException::new);
        this.repository.delete(model);
    }
}
