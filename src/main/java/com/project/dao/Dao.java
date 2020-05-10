package com.project.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao <T , Id extends Serializable> {

    public T findById(int id);

    public List<T> findAll();

    public void remove (T entity);

    public void persist (T entity);

    public void merge(T entity);
}
