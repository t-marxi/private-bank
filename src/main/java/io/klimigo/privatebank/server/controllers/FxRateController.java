package io.klimigo.privatebank.server.controllers;

import com.google.inject.Singleton;
import io.klimigo.privatebank.server.dto.Account;
import io.klimigo.privatebank.server.dto.AccountApplication;
import io.klimigo.privatebank.server.dto.FxRate;
import io.klimigo.privatebank.server.dto.FxRateApplication;
import io.klimigo.privatebank.server.service.AccountService;
import io.klimigo.privatebank.server.service.FxRateService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/fxrate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class FxRateController {

    private final FxRateService fxRateService;

    @Inject
    public FxRateController(FxRateService fxRateService) {
        this.fxRateService = fxRateService;
    }

    @POST
    public FxRate createNew(FxRateApplication fxRateApplication) {
        return fxRateService.createNew(fxRateApplication);
    }

    @GET
    @Path("/{from}/{to}")
    public FxRate getLastFxRate(@PathParam("from")Long currencyFrom, @PathParam("to")Long currencyTo) {
        return fxRateService.getLastFxRate(currencyFrom, currencyTo);
    }
}
