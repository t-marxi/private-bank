package io.klimigo.privatebank.server.execution.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import io.klimigo.privatebank.server.dto.FxRate;
import io.klimigo.privatebank.server.mapper.*;
import io.klimigo.privatebank.server.mapper.impl.*;
import io.klimigo.privatebank.server.service.*;
import io.klimigo.privatebank.server.service.impl.*;
import utility.Globals;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        Names.bindProperties(binder(), Globals.initializeConfig());
        bind(ClientService.class).to(ClientServiceImpl.class);
        bind(ClientMapper.class).to(ClientMapperImpl.class);
        bind(AccountService.class).to(AccountServiceImpl.class);
        bind(AccountMapper.class).to(AccountMapperImpl.class);
        bind(CurrencyService.class).to(CurrencyServiceImpl.class);
        bind(CurrencyMapper.class).to(CurrencyMapperImpl.class);
        bind(FxRateService.class).to(FxRateServiceImpl.class);
        bind(FxRateMapper.class).to(FxRateMapperImpl.class);
        bind(TransactionService.class).to(TransactionServiceImpl.class);
        bind(TransactionMapper.class).to(TransactionMapperImpl.class);
    }
}
