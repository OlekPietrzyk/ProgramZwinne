package com.project.dao;

import com.project.model.Zadanie;
import java.io.Serializable;
import java.util.List;

public interface ZadanieDao <T extends Zadanie, Id extends Serializable> extends Dao {

    public List<T> findByName(String name);

}
