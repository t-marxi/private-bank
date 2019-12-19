package io.klimigo.privatebank.server.mapper.impl;

import io.klimigo.privatebank.server.dto.Currency;
import io.klimigo.privatebank.server.entity.CurrencyEntity;
import io.klimigo.privatebank.server.mapper.CurrencyMapper;

import javax.inject.Singleton;

@Singleton
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public CurrencyEntity map(Currency currency) {
        CurrencyEntity entity = new CurrencyEntity();
        entity.setId(currency.getId());
        entity.setCode(currency.getCode());
        entity.setDescription(currency.getDescription());
        return entity;
    }

    @Override
    public Currency map(CurrencyEntity entity) {
        Currency currency = new Currency();
        currency.setId(entity.getId());
        currency.setCode(entity.getCode());
        currency.setDescription(entity.getDescription());
        return currency;
    }
}
