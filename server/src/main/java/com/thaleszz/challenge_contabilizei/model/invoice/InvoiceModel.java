package com.thaleszz.challenge_contabilizei.model.invoice;

import com.thaleszz.challenge_contabilizei.model.client.ClientModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_INVOICE")
@Getter
@Setter
public class InvoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String number;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime emissionDate;

    @Column
    private String description; // TODO check if its an enum according to challenge

    @Column
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;
}
