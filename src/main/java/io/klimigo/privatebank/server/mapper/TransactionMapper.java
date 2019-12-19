package io.klimigo.privatebank.server.mapper;

import io.klimigo.privatebank.server.dto.Transaction;
import io.klimigo.privatebank.server.dto.TransactionApplication;
import io.klimigo.privatebank.server.entity.TransactionEntity;


public interface TransactionMapper {
    Transaction map(TransactionEntity entity);

    TransactionEntity map(TransactionApplication transaction);

}
