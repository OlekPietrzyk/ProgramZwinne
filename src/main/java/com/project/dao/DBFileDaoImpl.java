package com.project.dao;

import com.project.model.DBFile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class DBFileDaoImpl implements DBFileDao<DBFile, Integer> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public DBFile findById(String id) {
        return (DBFile) entityManager.createNamedQuery("DBFile.getDBFileById").setParameter("id", id).getSingleResult();
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return entityManager.createNamedQuery("DBFile.getAll").getResultList();
    }

    @Override
    public List findByZadanieId(int zadanieId) {
        return entityManager.createNamedQuery("DBFile.getDBFileByIdTask").setParameter("zadanieId", zadanieId).getResultList();
    }

    @Override
    public void remove(Object entity) {
        entityManager.remove(entity);
    }

    @Override
    public void persist(Object entity) {
        entityManager.persist(entity);
    }

    @Override
    public void merge(Object entity) {
        entityManager.persist(entity);
    }

}
