package io.klimigo.privatebank.server.mapper.impl;

import io.klimigo.privatebank.server.dto.FxRate;
import io.klimigo.privatebank.server.dto.FxRateApplication;
import io.klimigo.privatebank.server.entity.FxRateEntity;
import io.klimigo.privatebank.server.mapper.FxRateMapper;

import javax.inject.Singleton;
import java.time.LocalDateTime;

@Singleton
public class FxRateMapperImpl implements FxRateMapper {

    @Override
    public FxRate map(FxRateEntity entity) {
        FxRate fxRate = new FxRate();
        fxRate.setId(entity.getId());
        fxRate.setCurrencyFrom(entity.getCurrencyFrom().getId());
        fxRate.setCurrencyTo(entity.getCurrencyTo().getId());
        fxRate.setValue(entity.getValue());
        return fxRate;
    }

    @Override
    public FxRateEntity map(FxRateApplication fxRate) {
        FxRateEntity entity = new FxRateEntity();
        entity.setValue(fxRate.getValue());
        entity.setDateTime(LocalDateTime.now());
        return entity;
    }
}
