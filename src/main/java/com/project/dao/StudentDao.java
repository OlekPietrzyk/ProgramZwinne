package com.project.dao;

import com.project.model.Student;
import java.io.Serializable;

public interface StudentDao<T extends Student, Id extends Serializable> extends Dao {



}
