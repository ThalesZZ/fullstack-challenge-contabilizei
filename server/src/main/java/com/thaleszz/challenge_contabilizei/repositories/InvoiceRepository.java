package com.thaleszz.challenge_contabilizei.repositories;

import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, UUID> {
}
