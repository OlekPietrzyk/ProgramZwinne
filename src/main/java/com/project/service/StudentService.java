package com.project.service;

import com.project.dao.StudentDao;
import com.project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public void save(Student student) {
        studentDao.persist(student);
    }

}
