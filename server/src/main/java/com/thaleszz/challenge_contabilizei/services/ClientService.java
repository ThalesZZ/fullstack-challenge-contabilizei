package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.client.Client;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Client create(@NotNull Client model);

    List<Client> list();

    Optional<Client> get(@NotNull UUID id);

    void delete(@NotNull UUID id);
}
