package com.thaleszz.challenge_contabilizei.controllers;

import com.thaleszz.challenge_contabilizei.dto.models.CreateClientRequest;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.models.user.User;
import com.thaleszz.challenge_contabilizei.services.ClientService;
import com.thaleszz.challenge_contabilizei.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {

    private final UserService userService;
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> list() {
        List<Client> clients = this.clientService.list();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody @Valid CreateClientRequest data) {
        User user = this.userService.get(data.userId()).orElseThrow(EntityNotFoundException::new);
        Client model = new Client(data);
        model.setUser(user);
        Client client = this.clientService.create(model);
        return ResponseEntity.ok(client); // TODO create response record to omit sensitive info from client
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        this.clientService.delete(id);
        return ResponseEntity.ok().build();
    }

}
