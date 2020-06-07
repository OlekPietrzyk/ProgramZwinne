package com.project.dao;

import java.io.Serializable;
import java.util.List;

public interface PersonDao<T, Id extends Serializable> {

    public void persist(T entity);

    public void update(T entity);

    public void remove(T entity);

    public T findById(Id id);

    public List<T> findAll();

    public T findAllByEmail(String email);
}
