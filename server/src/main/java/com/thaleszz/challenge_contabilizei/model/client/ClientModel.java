package com.thaleszz.challenge_contabilizei.model.client;

import com.thaleszz.challenge_contabilizei.model.invoice.InvoiceModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENT")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "regime")
@Getter
@Setter
public class ClientModel {
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

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceModel> invoices;

    // @Column
    // TODO private String attachments;
}
