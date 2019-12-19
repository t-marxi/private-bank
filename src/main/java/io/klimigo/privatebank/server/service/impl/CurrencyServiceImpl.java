package io.klimigo.privatebank.server.service.impl;

import io.klimigo.privatebank.server.dto.Currency;
import io.klimigo.privatebank.server.entity.CurrencyEntity;
import io.klimigo.privatebank.server.mapper.CurrencyMapper;
import io.klimigo.privatebank.server.repository.impl.CurrencyRepository;
import io.klimigo.privatebank.server.service.CurrencyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyMapper currencyMapper;
    private final CurrencyRepository currencyRepository;

    @Inject
    public CurrencyServiceImpl(CurrencyMapper currencyMapper, CurrencyRepository currencyRepository) {
        this.currencyMapper = currencyMapper;
        this.currencyRepository = currencyRepository;
    }


    @Override
    public Currency createNew(Currency currency) {
        CurrencyEntity entity = currencyMapper.map(currency);
        entity = currencyRepository.save(entity);
        return currencyMapper.map(entity);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll().stream()
                .map(currencyMapper::map)
                .collect(Collectors.toList());
    }
}
