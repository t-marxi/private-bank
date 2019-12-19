package io.klimigo.privatebank.server.mapper.impl;

import io.klimigo.privatebank.server.dto.FxRate;
import io.klimigo.privatebank.server.dto.FxRateApplication;
import io.klimigo.privatebank.server.dto.Transaction;
import io.klimigo.privatebank.server.dto.TransactionApplication;
import io.klimigo.privatebank.server.entity.FxRateEntity;
import io.klimigo.privatebank.server.entity.TransactionEntity;
import io.klimigo.privatebank.server.mapper.FxRateMapper;
import io.klimigo.privatebank.server.mapper.TransactionMapper;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Singleton
public class TransactionMapperImpl implements TransactionMapper {


    @Override
    public Transaction map(TransactionEntity entity) {
        Transaction dto = new Transaction();
        dto.setId(entity.getId());
        dto.setAccountFrom(entity.getFrom().getId());
        dto.setAccountTo(entity.getTo().getId());
        dto.setFxRateId(entity.getFxRate().getId());
        dto.setValueFrom(entity.getValueFrom());
        dto.setValueTo(entity.getValueTo());
        return null;
    }

    @Override
    public TransactionEntity map(TransactionApplication transactionApplication) {
        TransactionEntity entity = new TransactionEntity();
        entity.setDateTime(LocalDateTime.now());
        entity.setValueFrom(transactionApplication.getValue());
        return entity;
    }
}
