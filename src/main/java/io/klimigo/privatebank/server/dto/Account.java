package io.klimigo.privatebank.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private Long id;
    private Client client;
    private Currency currency;
    private BigDecimal value;
}
