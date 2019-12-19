package io.klimigo.privatebank.server.service.impl;

import io.klimigo.privatebank.server.dto.FxRate;
import io.klimigo.privatebank.server.dto.FxRateApplication;
import io.klimigo.privatebank.server.entity.FxRateEntity;
import io.klimigo.privatebank.server.mapper.FxRateMapper;
import io.klimigo.privatebank.server.repository.impl.CurrencyRepository;
import io.klimigo.privatebank.server.repository.impl.FxRateRepository;
import io.klimigo.privatebank.server.service.FxRateService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FxRateServiceImpl implements FxRateService {

    private final FxRateMapper fxRateMapper;
    private final FxRateRepository fxRateRepository;
    private final CurrencyRepository currencyRepository;

    @Inject
    public FxRateServiceImpl(FxRateMapper fxRateMapper, FxRateRepository fxRateRepository, CurrencyRepository currencyRepository) {
        this.fxRateMapper = fxRateMapper;
        this.fxRateRepository = fxRateRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public FxRate createNew(FxRateApplication fxRateApplication) {
        FxRateEntity entity = fxRateMapper.map(fxRateApplication);
        entity.setCurrencyFrom(currencyRepository.find(fxRateApplication.getCurrencyFrom()));
        entity.setCurrencyTo(currencyRepository.find(fxRateApplication.getCurrencyTo()));
        entity = fxRateRepository.save(entity);
        return fxRateMapper.map(entity);
    }

    @Override
    public FxRate getLastFxRate(Long currencyFrom, Long currencyTo) {
        FxRateEntity last = fxRateRepository.findLast(currencyFrom, currencyTo);
        return fxRateMapper.map(last);
    }
}
