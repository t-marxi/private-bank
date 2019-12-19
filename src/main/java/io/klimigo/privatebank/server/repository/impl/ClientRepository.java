package io.klimigo.privatebank.server.repository.impl;

import io.klimigo.privatebank.server.entity.ClientEntity;
import io.klimigo.privatebank.server.repository.AbstractRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class ClientRepository extends AbstractRepository<ClientEntity, Long> {

    @Inject
    public ClientRepository(EntityManager entityManager) {
        super(entityManager, ClientEntity.class);
    }

    public List<ClientEntity> findAll() {
        List resultList = getEntityManager()
                .createQuery("select e from ClientEntity e")
                .getResultList();
        return resultList;
    }
}
