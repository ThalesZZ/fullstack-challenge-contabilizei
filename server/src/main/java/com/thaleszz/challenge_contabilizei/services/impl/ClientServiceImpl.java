package com.thaleszz.challenge_contabilizei.services.impl;

import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.repositories.ClientRepository;
import com.thaleszz.challenge_contabilizei.services.ClientService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Override
    public Client create(@NotNull Client model) {
        return this.repository.save(model);
    }

    @Override
    public List<Client> list() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Client> get(@NotNull UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(@NotNull UUID id) {
        this.repository.deleteById(id);
    }

}
