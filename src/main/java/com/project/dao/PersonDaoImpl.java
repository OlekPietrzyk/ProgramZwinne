package com.project.dao;

import com.project.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class PersonDaoImpl implements PersonDao<Person, Integer> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void persist(Person entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void update(Person entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void remove(Person entity) {
        entityManager.remove(entity);
    }

    @Override
    public Person findById(Integer id) {
        return (Person) entityManager.createNamedQuery("Person.getPersonById").setParameter("id", id).getSingleResult();
    }

    @Override
    public List findAll() {
        return entityManager.createNamedQuery("Person.getAll").getResultList();
    }

    @Override
    public Person findAllByEmail(String email) throws NoResultException {
        List resultList = entityManager.createNamedQuery("Person.getPersonByEmail").setParameter("email", email).getResultList();
        if (!resultList.isEmpty()){
            return (Person) resultList.get(0);
        }
        return null;
    }
}
