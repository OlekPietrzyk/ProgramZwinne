package com.project.dao;

import com.project.model.Projekt;
import java.io.Serializable;
import java.util.List;

public interface ProjectDao <T  extends Projekt, Id extends Serializable> extends Dao {

    public List<T> findByName(String name);
}
