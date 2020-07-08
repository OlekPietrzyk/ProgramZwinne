package com.project.service;

import com.project.dao.DBFileDao;
import com.project.model.DBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBFileService {

    @Autowired
    private DBFileDao dBFileDao;

    public void save(DBFile dBFile) {
        dBFileDao.persist(dBFile);
    }

    public List<DBFile> findByZadanieId(int zadanieId) {
        return dBFileDao.findByZadanieId(zadanieId);
    }

    public DBFile findById(String fileId){
       return (DBFile) dBFileDao.findById(fileId);
    }

}
