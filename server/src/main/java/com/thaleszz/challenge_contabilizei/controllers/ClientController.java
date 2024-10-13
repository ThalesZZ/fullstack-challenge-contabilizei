package com.thaleszz.challenge_contabilizei.controllers;

import com.thaleszz.challenge_contabilizei.dto.models.ClientDTO;
import com.thaleszz.challenge_contabilizei.models.client.ClientModel;
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
    public ResponseEntity<List<ClientModel>> list() {
        List<ClientModel> clients = this.service.list();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<ClientModel> create(@RequestBody ClientDTO data) {
        ClientModel client = this.service.create(data);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
