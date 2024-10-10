package com.thaleszz.challenge_contabilizei.model.invoice;

import com.thaleszz.challenge_contabilizei.model.client.ClientModel;
import com.thaleszz.challenge_contabilizei.model.enums.Attachment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_INVOICE")
@Getter
@Setter
public class InvoiceModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String number;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Attachment attachment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime emissionDate;

    @Column
    private String description; // TODO check if its an enum according to challenge

    @Column(nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;
}
