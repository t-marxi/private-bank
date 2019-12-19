package io.klimigo.privatebank.server.repository.impl;

import io.klimigo.privatebank.server.entity.FxRateEntity;
import io.klimigo.privatebank.server.repository.AbstractRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

@Singleton
public class FxRateRepository extends AbstractRepository<FxRateEntity, Long> {

    @Inject
    public FxRateRepository(EntityManager entityManager) {
        super(entityManager, FxRateEntity.class);
    }

    public FxRateEntity findLast(Long currencyFrom, Long currencyTo) {
        FxRateEntity fxRateEntity = (FxRateEntity) getEntityManager()
                .createQuery("select e from FxRateEntity e " +
                        "where e.currencyFrom.id = :currencyFrom and e.currencyTo.id = :currencyTo " +
                        "order by e.dateTime desc")
                .setParameter("currencyFrom", currencyFrom)
                .setParameter("currencyTo", currencyTo)
                .setMaxResults(1)
                .getSingleResult();
        return fxRateEntity;
    }
}
