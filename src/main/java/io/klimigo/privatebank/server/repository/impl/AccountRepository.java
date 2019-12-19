package io.klimigo.privatebank.server.repository.impl;

import io.klimigo.privatebank.server.entity.AccountEntity;
import io.klimigo.privatebank.server.entity.ClientEntity;
import io.klimigo.privatebank.server.repository.AbstractRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class AccountRepository extends AbstractRepository<AccountEntity, Long> {

    @Inject
    public AccountRepository(EntityManager entityManager) {
        super(entityManager, AccountEntity.class);
    }

    public List<AccountEntity> findAll() {
        List resultList = getEntityManager()
                .createQuery("select e from AccountEntity e")
                .getResultList();
        return resultList;
    }

    public List<AccountEntity> findAllByClientId(Long clientId) {
        List resultList = getEntityManager()
                .createQuery("select e from AccountEntity e where e.client.id = :clientId")
                .setParameter("clientId", clientId)
                .getResultList();
        return resultList;
    }
}
