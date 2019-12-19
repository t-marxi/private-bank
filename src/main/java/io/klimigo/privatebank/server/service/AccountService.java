package io.klimigo.privatebank.server.service;

import io.klimigo.privatebank.server.dto.*;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    Account createNew(AccountApplication accountApplication);

    List<AccountOverview> getAllAccountOverviews();

    Long delete(Long accountId);

    Account getAccount(Long accountId);
}
