package com.thaleszz.challenge_contabilizei.models.client;

import com.thaleszz.challenge_contabilizei.dto.models.ClientResponse;
import com.thaleszz.challenge_contabilizei.dto.models.CreateClientRequest;
import com.thaleszz.challenge_contabilizei.models.invoice.Invoice;
import com.thaleszz.challenge_contabilizei.models.tax.Tax;
import com.thaleszz.challenge_contabilizei.models.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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
public class Client implements Serializable {
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
    private User user;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tax> taxes;

    public static Client fromRequest(@Valid CreateClientRequest data) {
        Client client = new Client();
        BeanUtils.copyProperties(data, client);
        return client;
    }

    public ClientResponse toResponse() {
        return new ClientResponse(
                this.companyName,
                this.cnpj,
                this.regime);
    }
}
