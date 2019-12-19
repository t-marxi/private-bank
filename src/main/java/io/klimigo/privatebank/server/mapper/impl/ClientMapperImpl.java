package io.klimigo.privatebank.server.mapper.impl;

import io.klimigo.privatebank.server.dto.AccountOverview;
import io.klimigo.privatebank.server.dto.Client;
import io.klimigo.privatebank.server.dto.ClientApplication;
import io.klimigo.privatebank.server.dto.ClientOverview;
import io.klimigo.privatebank.server.entity.AccountEntity;
import io.klimigo.privatebank.server.entity.ClientEntity;
import io.klimigo.privatebank.server.mapper.AccountMapper;
import io.klimigo.privatebank.server.mapper.ClientMapper;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Singleton
public class ClientMapperImpl implements ClientMapper {

    private final AccountMapper accountMapper;

    @Inject
    public ClientMapperImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public ClientEntity map(ClientApplication clientApplication) {
        if (isNull(clientApplication)) {
            return null;
        }
        ClientEntity entity = new ClientEntity();
        entity.setName(clientApplication.getName());
        entity.setSecondName(clientApplication.getSecondName());
        entity.setLastName(clientApplication.getLastName());
        return entity;
    }

    @Override
    public Client map(List<AccountEntity> accountsEntities) {
        if (CollectionUtils.isEmpty(accountsEntities)) {
            return null;
        }
        ClientEntity entity = accountsEntities.get(0).getClient();
        Client client = map(entity);
        List<AccountOverview> accountOverviews = accountsEntities.stream()
                .map(accountMapper::mapToOverview)
                .collect(Collectors.toList());
        client.setAccounts(accountOverviews);
        return client;
    }

    @Override
    public Client map(ClientEntity entity) {
        Client client = new Client();
        client.setId(entity.getId());
        client.setName(entity.getName());
        client.setSecondName(entity.getSecondName());
        client.setLastName(entity.getLastName());
        return client;
    }

    @Override
    public ClientOverview mapToOverview(ClientEntity entity) {
        if (isNull(entity)) {
            return null;
        }
        ClientOverview client = new ClientOverview();
        client.setId(entity.getId());
        client.setName(entity.getName());
        client.setSecondName(entity.getSecondName());
        client.setLastName(entity.getLastName());
        return client;
    }
}
