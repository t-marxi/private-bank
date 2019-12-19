package io.klimigo.privatebank.server.repository.impl;

import io.klimigo.privatebank.server.entity.AccountEntity;
import io.klimigo.privatebank.server.entity.CurrencyEntity;
import io.klimigo.privatebank.server.repository.AbstractRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class CurrencyRepository extends AbstractRepository<CurrencyEntity, Long> {

    @Inject
    public CurrencyRepository(EntityManager entityManager) {
        super(entityManager, CurrencyEntity.class);
    }

    public List<CurrencyEntity> findAll() {
        List resultList = getEntityManager()
                .createQuery("select e from CurrencyEntity e")
                .getResultList();
        return resultList;
    }
}
