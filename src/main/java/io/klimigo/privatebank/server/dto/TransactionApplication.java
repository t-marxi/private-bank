package io.klimigo.privatebank.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionApplication {
    private Long accountFrom;
    private Long accountTo;
    private BigDecimal value;
}
