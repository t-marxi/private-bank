package io.klimigo.privatebank.server.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FxRate {
    private Long id;
    private Long currencyFrom;
    private Long currencyTo;
    private BigDecimal value;
}
