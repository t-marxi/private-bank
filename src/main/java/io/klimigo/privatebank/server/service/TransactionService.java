package io.klimigo.privatebank.server.service;

import io.klimigo.privatebank.server.dto.FxRate;
import io.klimigo.privatebank.server.dto.FxRateApplication;
import io.klimigo.privatebank.server.dto.Transaction;
import io.klimigo.privatebank.server.dto.TransactionApplication;

import java.util.List;

public interface TransactionService {

    Transaction createNew(TransactionApplication transactionApplication);

    List<Transaction> getTransactions(Long accountFrom, Long accountTo);
}
