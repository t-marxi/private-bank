package io.klimigo.privatebank.server.controllers;

import com.google.inject.Singleton;
import io.klimigo.privatebank.server.dto.Account;
import io.klimigo.privatebank.server.dto.AccountApplication;
import io.klimigo.privatebank.server.dto.AccountOverview;
import io.klimigo.privatebank.server.dto.Currency;
import io.klimigo.privatebank.server.service.AccountService;
import io.klimigo.privatebank.server.service.CurrencyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/currency")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class CurrencyController {

    private CurrencyService currencyService;

    @Inject
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GET
    public List<Currency> getAll() {
        return currencyService.getAllCurrencies();
    }

    @POST
    public Currency createNew(Currency currency) {
        return currencyService.createNew(currency);
    }
}
