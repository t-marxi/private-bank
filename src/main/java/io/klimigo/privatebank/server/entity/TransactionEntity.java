package io.klimigo.privatebank.server.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(nullable = false, name = "account_from_id")
    private AccountEntity from;

    @ManyToOne
    @JoinColumn(nullable = false, name = "account_to_id")
    private AccountEntity to;

    @ManyToOne
    @JoinColumn(nullable = true, name = "fxrate_id")
    private FxRateEntity fxRate;

    @Column(nullable = false)
    private BigDecimal valueFrom;

    @Column(nullable = false)
    private BigDecimal valueTo;
}
