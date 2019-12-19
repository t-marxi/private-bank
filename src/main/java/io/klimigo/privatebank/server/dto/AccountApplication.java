package io.klimigo.privatebank.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountApplication {
    private Long clientId;
    private Long currencyId;
    private BigDecimal startValue;
}
