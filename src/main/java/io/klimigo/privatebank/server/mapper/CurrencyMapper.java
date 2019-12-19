package io.klimigo.privatebank.server.mapper;

import io.klimigo.privatebank.server.dto.Currency;
import io.klimigo.privatebank.server.entity.CurrencyEntity;

public interface CurrencyMapper {

    CurrencyEntity map(Currency currency);

    Currency map(CurrencyEntity entity);

}
