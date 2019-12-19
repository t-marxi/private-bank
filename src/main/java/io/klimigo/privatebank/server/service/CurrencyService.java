package io.klimigo.privatebank.server.service;

import io.klimigo.privatebank.server.dto.Currency;

import java.util.List;

public interface CurrencyService {

    Currency createNew(Currency currency);

    List<Currency> getAllCurrencies();
}
