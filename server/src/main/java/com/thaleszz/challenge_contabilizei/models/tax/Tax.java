package com.thaleszz.challenge_contabilizei.models.tax;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_TAX")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tax implements Serializable {
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
    @Convert(converter = YearMonthConverter.class)
    private YearMonth referenceDate;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private boolean paid;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("taxes")
    private Client client;

    protected static class YearMonthConverter implements AttributeConverter<YearMonth, String> {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

        @Override
        public String convertToDatabaseColumn(YearMonth attribute) {
            return Objects.nonNull(attribute) ? attribute.format(FORMATTER) : null;
        }

        @Override
        public YearMonth convertToEntityAttribute(String dbData) {
            return Objects.nonNull(dbData) ? YearMonth.parse(dbData, FORMATTER) : null;
        }
    }
}