package com.thaleszz.challenge_contabilizei.controllers;

import com.thaleszz.challenge_contabilizei.dto.models.InvoiceDTO;
import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceModel;
import com.thaleszz.challenge_contabilizei.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoice")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService service;

    @GetMapping
    public ResponseEntity<List<InvoiceModel>> list() {
        List<InvoiceModel> clients = this.service.list();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<InvoiceModel> create(@RequestBody InvoiceDTO data) {
        InvoiceModel client = this.service.create(data);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
