package io.klimigo.privatebank.server.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long id;
    private Long accountFrom;
    private Long accountTo;
    private Long fxRateId;
    private BigDecimal valueFrom;
    private BigDecimal valueTo;
}
