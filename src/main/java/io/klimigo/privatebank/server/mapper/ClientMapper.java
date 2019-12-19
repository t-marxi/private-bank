package io.klimigo.privatebank.server.mapper;

import io.klimigo.privatebank.server.dto.Client;
import io.klimigo.privatebank.server.dto.ClientApplication;
import io.klimigo.privatebank.server.dto.ClientOverview;
import io.klimigo.privatebank.server.entity.AccountEntity;
import io.klimigo.privatebank.server.entity.ClientEntity;

import java.util.List;

public interface ClientMapper {

    ClientEntity map(ClientApplication clientApplication);

    Client map(List<AccountEntity> accountsEntities);

    Client map(ClientEntity entity);

    ClientOverview mapToOverview(ClientEntity entity);
}
