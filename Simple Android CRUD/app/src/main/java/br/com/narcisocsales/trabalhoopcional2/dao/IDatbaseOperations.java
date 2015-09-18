package br.com.narcisocsales.trabalhoopcional2.dao;

import java.util.List;

/**
 * Created by bruno on 30/08/15.
 */
public interface IDatbaseOperations<T> {

    public void insert(T obj);

    public void update(T obj);

    public void delete(T obj);

    public T find(Long id);

    public List<T> findAll();

}
