package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.dto.models.ClientDTO;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.repositories.ClientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public Client create(@Valid ClientDTO data) {
        Client model = new Client(data);
        return this.repository.save(model);
    }

    public List<Client> list() {
        return this.repository.findAll();
    }

    public Optional<Client> get(@NotNull UUID id) {
        return this.repository.findById(id);
    }

    public void delete(@NotNull UUID id) {
        this.repository.deleteById(id);
    }

}
