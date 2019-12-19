package io.klimigo.privatebank.server.mapper;

import io.klimigo.privatebank.server.dto.Account;
import io.klimigo.privatebank.server.dto.AccountOverview;
import io.klimigo.privatebank.server.dto.FxRate;
import io.klimigo.privatebank.server.dto.FxRateApplication;
import io.klimigo.privatebank.server.entity.AccountEntity;
import io.klimigo.privatebank.server.entity.FxRateEntity;

public interface FxRateMapper {
    FxRate map(FxRateEntity entity);

    FxRateEntity map(FxRateApplication fxRate);
}
