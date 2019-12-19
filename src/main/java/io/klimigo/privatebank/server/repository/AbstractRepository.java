package io.klimigo.privatebank.server.repository;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public abstract class AbstractRepository<T, ID> implements Repository<T, ID> {

    private final EntityManager entityManager;
    private final Class<T> persistedClass;

    @Inject
    public AbstractRepository(EntityManager entityManager, Class<T> persistedClass) {
        this.persistedClass = persistedClass;
        this.entityManager = entityManager;
    }

    @Override
    public T save(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entity;
    }

    @Override
    public T find(ID id) {
        return entityManager.find(persistedClass, id);
    }

    @Override
    public T delete(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entity);
        transaction.commit();
        return entity;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
