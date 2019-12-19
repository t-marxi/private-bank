package io.klimigo.privatebank.server.repository;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class DBModule extends AbstractModule {

    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<>();

    public void configure() {
    }

    @Provides
    @Singleton
    public EntityManagerFactory provideEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("db-manager");
    }

    @Provides
    public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
        if (entityManager == null) {
            ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
        }
        return entityManager;
    }

}