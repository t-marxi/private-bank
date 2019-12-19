package io.klimigo.privatebank.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FxRateApplication {
    private Long currencyFrom;
    private Long currencyTo;
    private BigDecimal value;
}
