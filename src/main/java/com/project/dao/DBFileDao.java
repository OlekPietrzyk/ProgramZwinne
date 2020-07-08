package com.project.dao;

import com.project.model.DBFile;

import java.io.Serializable;
import java.util.List;

public interface DBFileDao<T extends DBFile, Id extends Serializable> extends Dao {

    List findByZadanieId(int zadanieId);

    public T findById(String id);
}