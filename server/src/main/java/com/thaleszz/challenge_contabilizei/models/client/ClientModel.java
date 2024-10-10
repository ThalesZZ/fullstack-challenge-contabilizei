package com.thaleszz.challenge_contabilizei.models.client;

import com.thaleszz.challenge_contabilizei.models.enums.Attachment;
import com.thaleszz.challenge_contabilizei.models.enums.TaxRegime;
import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceModel;
import com.thaleszz.challenge_contabilizei.models.tax.TaxModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENT")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "regime")
@Getter
@Setter
public class ClientModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "client_id")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaxRegime regime;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Attachment attachment; // only for "Simples Nacional" regime, null for "Lucro Presumido" regime

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceModel> invoices;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaxModel> taxes;
}
