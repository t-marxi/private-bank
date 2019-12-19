package io.klimigo.privatebank.server.controllers;

import javax.inject.Inject;
import com.google.inject.Singleton;
import io.klimigo.privatebank.server.dto.Client;
import io.klimigo.privatebank.server.dto.ClientApplication;
import io.klimigo.privatebank.server.dto.ClientOverview;
import io.klimigo.privatebank.server.service.ClientService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class ClientController {

    private ClientService clientService;

    @Inject
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GET
    public List<ClientOverview> getAll() {
        return clientService.getAllClientOverviews();
    }

    @POST
    public Client createNew(ClientApplication clientApplication) {
        return clientService.createNew(clientApplication);
    }

    @DELETE
    @Path("/{id}")
    public Long delete(@PathParam("id")Long clientId) {
        return clientService.delete(clientId);
    }

    @GET
    @Path("/{id}")
    public Client getClient(@PathParam("id")Long clientId) {
        return clientService.getClient(clientId);
    }
}
