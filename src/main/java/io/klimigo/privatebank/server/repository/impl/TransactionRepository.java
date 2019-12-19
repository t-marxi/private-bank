package io.klimigo.privatebank.server.repository.impl;

import io.klimigo.privatebank.server.entity.TransactionEntity;
import io.klimigo.privatebank.server.repository.AbstractRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Singleton
public class TransactionRepository extends AbstractRepository<TransactionEntity, Long> {

    private final AccountRepository accountRepository;

    @Inject
    public TransactionRepository(EntityManager entityManager, AccountRepository accountRepository) {
        super(entityManager, TransactionEntity.class);
        this.accountRepository = accountRepository;
    }

    @Override
    public TransactionEntity save(TransactionEntity entity) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
        getEntityManager().persist(entity);
        getEntityManager().persist(entity.getTo());
        getEntityManager().persist(entity.getFrom());
        transaction.commit();
        return entity;
    }

    public List<TransactionEntity> findAll(Long accountFrom, Long accountTo) {
        List result = getEntityManager()
                .createQuery("select e from TransactionEntity e " +
                        "where e.accountFrom.id = :accountFrom and e.accountTo.id = :accountTo")
                .setParameter("accountFrom", accountFrom)
                .setParameter("accountTo", accountTo)
                .getResultList();
        return result;
    }
}
