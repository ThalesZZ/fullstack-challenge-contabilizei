package com.thaleszz.challenge_contabilizei.models.tax;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_TAX")
@Getter
@Setter
public class TaxModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private ClientModel client;
}
