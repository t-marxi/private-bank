package io.klimigo.privatebank.server.mapper;

import io.klimigo.privatebank.server.dto.Account;
import io.klimigo.privatebank.server.dto.AccountOverview;
import io.klimigo.privatebank.server.entity.AccountEntity;

public interface AccountMapper {
    Account map(AccountEntity entity);

    AccountOverview mapToOverview(AccountEntity entity);
}
