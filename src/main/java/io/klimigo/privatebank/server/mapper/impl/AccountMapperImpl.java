package io.klimigo.privatebank.server.mapper.impl;

import io.klimigo.privatebank.server.dto.Account;
import io.klimigo.privatebank.server.dto.AccountOverview;
import io.klimigo.privatebank.server.entity.AccountEntity;
import io.klimigo.privatebank.server.mapper.AccountMapper;
import io.klimigo.privatebank.server.mapper.ClientMapper;
import io.klimigo.privatebank.server.mapper.CurrencyMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AccountMapperImpl implements AccountMapper {

    private final ClientMapper clientMapper;
    private final CurrencyMapper currencyMapper;

    @Inject
    public AccountMapperImpl(ClientMapper clientMapper, CurrencyMapper currencyMapper) {
        this.clientMapper = clientMapper;
        this.currencyMapper = currencyMapper;
    }

    @Override
    public Account map(AccountEntity entity) {
        Account account = new Account();
        account.setId(entity.getId());
        account.setCurrency(currencyMapper.map(entity.getCurrency()));
        account.setClient(clientMapper.map(entity.getClient()));
        account.setValue(entity.getValue());
        return account;
    }

    @Override
    public AccountOverview mapToOverview(AccountEntity entity) {
        AccountOverview account = new AccountOverview();
        account.setId(entity.getId());
        account.setCurrency(currencyMapper.map(entity.getCurrency()));
        account.setClientId(entity.getClient().getId());
        account.setValue(entity.getValue());
        return account;
    }
}
