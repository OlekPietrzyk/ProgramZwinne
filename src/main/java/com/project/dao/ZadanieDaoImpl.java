package com.project.dao;

import com.project.model.Zadanie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ZadanieDaoImpl implements ZadanieDao<Zadanie, Integer>{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Zadanie findById(int id) {
        List resultList = entityManager.createNamedQuery("Zadanie.getZadanieById").setParameter("id", id).getResultList();
        return resultList!=null ? (Zadanie) resultList.get(0) : null;
    }

    @Override
    public List findAll() {
        return entityManager.createNamedQuery("Zadanie.getAll").getResultList();
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

    @Override
    public List<Zadanie> findByName(String name) {
        return entityManager.createNamedQuery("Zadanie.getByName").setParameter("name", name).getResultList();
    }

    @Override
    public List<Zadanie> findByIdProject(int id) {
        return entityManager.createNamedQuery("Zadanie.getZadanieByIdProject").setParameter("id", id).getResultList();
    }
}
