package io.klimigo.privatebank.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountOverview {
    private Long id;
    private Currency currency;
    private Long clientId;
    private BigDecimal value;
}
