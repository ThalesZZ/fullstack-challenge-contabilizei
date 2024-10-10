package com.thaleszz.challenge_contabilizei.services;

import com.thaleszz.challenge_contabilizei.models.client.ClientDTO;
import com.thaleszz.challenge_contabilizei.models.client.ClientModel;
import com.thaleszz.challenge_contabilizei.repositories.ClientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public ClientModel create(@Valid ClientDTO data) {
        ClientModel model = new ClientModel(data);
        return this.repository.save(model);
    }

    public List<ClientModel> list() {
        return this.repository.findAll();
    }

}
