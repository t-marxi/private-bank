package io.klimigo.privatebank.server.service;

import io.klimigo.privatebank.server.dto.*;

import java.util.List;

public interface FxRateService {

    FxRate createNew(FxRateApplication fxRateApplication);

    FxRate getLastFxRate(Long currencyFrom, Long currencyTo);
}
