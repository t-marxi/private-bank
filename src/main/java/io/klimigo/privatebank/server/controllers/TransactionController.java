package io.klimigo.privatebank.server.controllers;

import com.google.inject.Singleton;
import io.klimigo.privatebank.server.dto.FxRate;
import io.klimigo.privatebank.server.dto.Transaction;
import io.klimigo.privatebank.server.dto.TransactionApplication;
import io.klimigo.privatebank.server.service.FxRateService;
import io.klimigo.privatebank.server.service.TransactionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class TransactionController {

    private final TransactionService transactionService;

    @Inject
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @POST
    public Transaction createNew(TransactionApplication transactionApplication) {
        return transactionService.createNew(transactionApplication);
    }

    @GET
    @Path("/{from}/{to}/{value}")
    public List<Transaction> getLastFxRate(@PathParam("from") Long accountFrom, @PathParam("to") Long accountTo) {
        return transactionService.getTransactions(accountFrom, accountTo);
    }
}
