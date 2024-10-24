package com.thaleszz.challenge_contabilizei.models.invoice;

import com.thaleszz.challenge_contabilizei.dto.models.CreateInvoiceRequest;
import com.thaleszz.challenge_contabilizei.dto.models.InvoiceResponse;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_INVOICE")
@Getter
@Setter
@NoArgsConstructor
public class Invoice implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String number;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Attachment attachment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime emissionDate;

    @Column(nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public static Invoice fromRequest(CreateInvoiceRequest data) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(data, invoice);
        return invoice;
    }

    public InvoiceResponse toResponse() {
        return new InvoiceResponse(
                this.number,
                this.attachment,
                this.emissionDate,
                this.value);
    }
}
