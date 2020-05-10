package com.project.dao;

import com.project.model.Projekt;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao<Projekt, Integer> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Projekt findById(int id) {
        List resultList = entityManager.createNamedQuery("Projekt.getProjektById").setParameter("id", id).getResultList();
        return !resultList.isEmpty() ? (Projekt) resultList.get(0) : null;
    }

    @Override
    public List findAll() {
        return entityManager.createNamedQuery("Projekt.getAll").getResultList();
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
    public List<Projekt> findByName(String name) {
        return entityManager.createNamedQuery("Projekt.getByName").setParameter("name", name).getResultList();
    }
}
