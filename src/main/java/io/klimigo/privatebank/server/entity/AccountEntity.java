package io.klimigo.privatebank.server.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
public class AccountEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "client_id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(nullable = false, name = "currency_id")
    private CurrencyEntity currency;

    @Column(nullable = false)
    private BigDecimal value;
}
