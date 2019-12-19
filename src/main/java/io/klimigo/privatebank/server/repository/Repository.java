package io.klimigo.privatebank.server.repository;


import java.util.List;

public interface Repository<T, ID> {
    T save(T entity);
    T find(ID id);
    T delete(T entity);
}
