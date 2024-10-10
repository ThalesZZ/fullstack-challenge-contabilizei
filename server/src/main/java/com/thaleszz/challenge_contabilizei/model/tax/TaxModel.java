package com.thaleszz.challenge_contabilizei.model.tax;

import com.thaleszz.challenge_contabilizei.model.client.ClientModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_TAX")
@Getter
@Setter
public class TaxModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaxType type;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(nullable = false)
    private Date referenceDate;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private boolean paid;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientModel client;
}
