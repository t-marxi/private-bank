package io.klimigo.privatebank.server.service.impl;

import com.google.inject.Singleton;
import io.klimigo.privatebank.server.dto.Client;
import io.klimigo.privatebank.server.dto.ClientApplication;
import io.klimigo.privatebank.server.dto.ClientOverview;
import io.klimigo.privatebank.server.entity.AccountEntity;
import io.klimigo.privatebank.server.entity.ClientEntity;
import io.klimigo.privatebank.server.mapper.ClientMapper;
import io.klimigo.privatebank.server.repository.impl.AccountRepository;
import io.klimigo.privatebank.server.repository.impl.ClientRepository;
import io.klimigo.privatebank.server.service.ClientService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Singleton
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    @Inject
    public ClientServiceImpl(ClientMapper clientMapper, ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Client createNew(ClientApplication client) {
        ClientEntity entity = clientMapper.map(client);
        entity = clientRepository.save(entity);
        return clientMapper.map(entity);
    }

    public List<ClientOverview> getAllClientOverviews() {
        List<ClientEntity> all = clientRepository.findAll();
        return all.stream()
                .map(clientMapper::mapToOverview)
                .collect(Collectors.toList());
    }

    @Override
    public Long delete(Long clientId) {
        ClientEntity entity = clientRepository.find(clientId);
        if (isNull(entity)) {
            throw  new IllegalArgumentException(String.format("There is no client with id %d", clientId));
        }
        ClientEntity deleted = clientRepository.delete(entity);
        return deleted.getId();
    }

    @Override
    public Client getClient(Long clientId) {
        List<AccountEntity> allByClientId = accountRepository.findAllByClientId(clientId);
        return clientMapper.map(allByClientId);
    }


}
