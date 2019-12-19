package io.klimigo.privatebank.server.service.impl;

import io.klimigo.privatebank.server.dto.FxRate;
import io.klimigo.privatebank.server.dto.Transaction;
import io.klimigo.privatebank.server.dto.TransactionApplication;
import io.klimigo.privatebank.server.entity.TransactionEntity;
import io.klimigo.privatebank.server.mapper.FxRateMapper;
import io.klimigo.privatebank.server.mapper.TransactionMapper;
import io.klimigo.privatebank.server.repository.impl.AccountRepository;
import io.klimigo.privatebank.server.repository.impl.FxRateRepository;
import io.klimigo.privatebank.server.repository.impl.TransactionRepository;
import io.klimigo.privatebank.server.service.TransactionService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final FxRateRepository fxRateRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final FxRateMapper fxRateMapper;

    @Inject
    public TransactionServiceImpl(AccountRepository accountRepository,
                                  FxRateRepository fxRateRepository,
                                  TransactionRepository transactionRepository,
                                  TransactionMapper transactionMapper,
                                  FxRateMapper fxRateMapper) {
        this.accountRepository = accountRepository;
        this.fxRateRepository = fxRateRepository;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.fxRateMapper = fxRateMapper;
    }

    @Override
    public Transaction createNew(TransactionApplication transactionApplication) {
        TransactionEntity entity = transactionMapper.map(transactionApplication);
        entity.setFrom(accountRepository.find(transactionApplication.getAccountFrom()));
        entity.setTo(accountRepository.find(transactionApplication.getAccountTo()));
        entity.setFxRate(fxRateRepository.findLast(transactionApplication.getAccountFrom(), transactionApplication.getAccountTo()));
        entity.setValueTo(entity.getValueFrom().divide(entity.getFxRate().getValue()));
        entity = transactionRepository.save(entity);
        return transactionMapper.map(entity);
    }

    @Override
    public List<Transaction> getTransactions(Long accountFrom, Long accountTo) {
        return transactionRepository.findAll(accountFrom, accountTo).stream()
                .map(transactionMapper::map)
                .collect(Collectors.toList());
    }
}
