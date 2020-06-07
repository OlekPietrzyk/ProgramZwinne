package com.project.dao;

import com.project.model.Lecturer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LecturerDaoImpl implements LecturerDao<Lecturer, Integer> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Lecturer findById(int id) {
        return (Lecturer) entityManager.createNamedQuery("Lecturer.getLecturerById").setParameter("id", id).getSingleResult();
    }

    @Override
    public List findAll() {
       return entityManager.createNamedQuery("Lecturer.getAll").getResultList();
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
        entityManager.merge(entity);
    }

}
