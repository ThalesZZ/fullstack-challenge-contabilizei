package com.thaleszz.challenge_contabilizei.controllers;

import com.thaleszz.challenge_contabilizei.dto.models.CreateInvoiceRequest;
import com.thaleszz.challenge_contabilizei.dto.models.InvoiceResponse;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.services.ClientService;
import com.thaleszz.challenge_contabilizei.services.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoice")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> list() {
        List<Invoice> clients = this.invoiceService.list();
        List<InvoiceResponse> response = clients.stream().map(Invoice::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<InvoiceResponse> create(@RequestBody @Valid CreateInvoiceRequest data) {
        Invoice model = Invoice.fromRequest(data);
        Client client = this.clientService.get(model.getClient().getId()).orElseThrow(EntityNotFoundException::new);
        Invoice invoice = this.invoiceService.create(client, model);
        InvoiceResponse response = invoice.toResponse();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        this.invoiceService.delete(id);
        return ResponseEntity.ok().build();
    }

}
