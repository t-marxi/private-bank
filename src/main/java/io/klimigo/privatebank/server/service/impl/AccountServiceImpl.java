package io.klimigo.privatebank.server.service.impl;

import com.google.inject.Singleton;
import io.klimigo.privatebank.server.dto.Account;
import io.klimigo.privatebank.server.dto.AccountApplication;
import io.klimigo.privatebank.server.dto.AccountOverview;
import io.klimigo.privatebank.server.entity.AccountEntity;
import io.klimigo.privatebank.server.entity.ClientEntity;
import io.klimigo.privatebank.server.entity.CurrencyEntity;
import io.klimigo.privatebank.server.mapper.AccountMapper;
import io.klimigo.privatebank.server.repository.impl.AccountRepository;
import io.klimigo.privatebank.server.repository.impl.ClientRepository;
import io.klimigo.privatebank.server.repository.impl.CurrencyRepository;
import io.klimigo.privatebank.server.service.AccountService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Singleton
public class AccountServiceImpl implements AccountService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final AccountMapper accountMapper;

    @Inject
    public AccountServiceImpl(ClientRepository clientRepository, AccountRepository accountRepository,
                              CurrencyRepository currencyRepository, AccountMapper accountMapper) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account createNew(AccountApplication accountApplication) {
        ClientEntity clientEntity = clientRepository.find(accountApplication.getClientId());
        if (isNull(clientEntity)) {
            throw new IllegalArgumentException(String.format("There is no client with id %d", accountApplication.getClientId()));
        }
        CurrencyEntity currencyEntity = currencyRepository.find(accountApplication.getCurrencyId());
        if (isNull(currencyEntity)) {
            throw new IllegalArgumentException(String.format("There is no currency with id %d", accountApplication.getCurrencyId()));
        }

        AccountEntity entity = new AccountEntity();
        entity.setClient(clientEntity);
        entity.setCurrency(currencyEntity);
        entity.setValue(isNull(accountApplication.getStartValue()) ? BigDecimal.ZERO : accountApplication.getStartValue());
        entity = accountRepository.save(entity);
        return accountMapper.map(entity);
    }

    @Override
    public List<AccountOverview> getAllAccountOverviews() {
        return accountRepository.findAll().stream()
                .map(accountMapper::mapToOverview)
                .collect(Collectors.toList());
    }

    @Override
    public Long delete(Long accountId) {
        AccountEntity entity = accountRepository.find(accountId);
        if (isNull(entity)) {
            throw new IllegalArgumentException(String.format("There is no client with id %d", accountId));
        }
        AccountEntity deleted = accountRepository.delete(entity);
        return deleted.getId();
    }

    @Override
    public Account getAccount(Long accountId) {
        AccountEntity entity = accountRepository.find(accountId);
        return accountMapper.map(entity);
    }
}
