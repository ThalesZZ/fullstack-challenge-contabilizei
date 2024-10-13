package com.thaleszz.challenge_contabilizei.models.client;

import com.thaleszz.challenge_contabilizei.dto.models.ClientDTO;
import com.thaleszz.challenge_contabilizei.models.invoice.InvoiceModel;
import com.thaleszz.challenge_contabilizei.models.tax.TaxModel;
import com.thaleszz.challenge_contabilizei.models.user.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENT")
@Getter
@Setter
@NoArgsConstructor
public class ClientModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String companyName;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaxRegime regime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceModel> invoices;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaxModel> taxes;

    public ClientModel(ClientDTO data) {
        BeanUtils.copyProperties(data, this);
    }
}
