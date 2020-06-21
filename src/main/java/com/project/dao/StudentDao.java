package com.project.dao;

import com.project.model.Student;
import java.io.Serializable;
import java.util.List;

public interface StudentDao<T extends Student, Id extends Serializable> extends Dao {

    public List<T> findByIds(List<Integer> ids);


}
