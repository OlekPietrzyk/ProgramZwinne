package com.project.service;

import com.project.dao.LecturerDao;
import com.project.model.Lecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerService {

    @Autowired
    private LecturerDao lecturerDao;

    public void save(Lecturer lecturer) {
        lecturerDao.persist(lecturer);
    }
}
