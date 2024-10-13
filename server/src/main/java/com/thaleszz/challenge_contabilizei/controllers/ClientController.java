package com.thaleszz.challenge_contabilizei.controllers;

import com.thaleszz.challenge_contabilizei.dto.models.ClientDTO;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> list() {
        List<Client> clients = this.service.list();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDTO data) {
        Client client = this.service.create(data);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
