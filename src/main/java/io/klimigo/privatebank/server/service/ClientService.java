package io.klimigo.privatebank.server.service;

import io.klimigo.privatebank.server.dto.Client;
import io.klimigo.privatebank.server.dto.ClientApplication;
import io.klimigo.privatebank.server.dto.ClientOverview;

import java.util.List;

public interface ClientService {

    Client createNew(ClientApplication client);

    List<ClientOverview> getAllClientOverviews();

    Long delete(Long clientId);

    Client getClient(Long clientId);
}
