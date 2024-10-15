package com.thaleszz.challenge_contabilizei.dto.models;

import com.thaleszz.challenge_contabilizei.models.client.TaxRegime;

public record ClientResponse(String companyName,
                             String cnpj,
                             String email,
                             TaxRegime regime) {
}
