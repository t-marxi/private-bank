package io.klimigo.privatebank.server.controllers;

import com.google.inject.Singleton;
import io.klimigo.privatebank.server.dto.*;
import io.klimigo.privatebank.server.service.AccountService;
import io.klimigo.privatebank.server.service.ClientService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class AccountController {

    private AccountService accountService;

    @Inject
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    public List<AccountOverview> getAll() {
        return accountService.getAllAccountOverviews();
    }

    @POST
    public Account createNew(AccountApplication accountApplication) {
        return accountService.createNew(accountApplication);
    }

    @DELETE
    public Long delete(Long accountId) {
        return accountService.delete(accountId);
    }

    @GET
    @Path("/{id}")
    public Account getAccount(@PathParam("id")Long accountId) {
        return accountService.getAccount(accountId);
    }
}
