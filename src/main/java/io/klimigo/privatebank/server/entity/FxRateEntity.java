package io.klimigo.privatebank.server.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fxrate")
@Data
public class FxRateEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(nullable = false, name = "currency_from_id")
    private CurrencyEntity currencyFrom;

    @ManyToOne
    @JoinColumn(nullable = false, name = "currency_to_id")
    private CurrencyEntity currencyTo;

    @Column(nullable = false)
    private BigDecimal value;
}
