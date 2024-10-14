package com.thaleszz.challenge_contabilizei.controllers;

import com.thaleszz.challenge_contabilizei.dto.models.CalculateTaxRequest;
import com.thaleszz.challenge_contabilizei.dto.models.PayTaxesRequest;
import com.thaleszz.challenge_contabilizei.dto.models.TaxResponse;
import com.thaleszz.challenge_contabilizei.models.tax.Tax;
import com.thaleszz.challenge_contabilizei.services.TaxService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tax")
@AllArgsConstructor
public class TaxController {

    private final TaxService taxService;

    @GetMapping("/{clientId}")
    public ResponseEntity<List<TaxResponse>> list(@PathVariable("clientId") UUID clientId) {
        List<Tax> taxes = this.taxService.getClientTaxes(clientId);
        List<TaxResponse> response = taxes.stream().map(Tax::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<List<TaxResponse>> calculate(@RequestBody @Valid CalculateTaxRequest data) {
        List<Tax> taxes = this.taxService.requestCalculation(data.clientId(), data.referenceDate());
        List<TaxResponse> response = taxes.stream().map(Tax::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<List<TaxResponse>> payment(@RequestBody @Valid PayTaxesRequest data) {
        List<Tax> paidTaxes = this.taxService.pay(data.taxesIds());
        List<TaxResponse> response = paidTaxes.stream().map(Tax::toResponse).toList();
        return ResponseEntity.ok(response);
    }

}
