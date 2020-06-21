package com.project.dao;

import com.project.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao<Student, Integer>{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Student findById(int id) {
        List resultList = entityManager.createNamedQuery("Student.getStudentById").setParameter("id", id).getResultList();
        return !resultList.isEmpty() ? (Student) resultList.get(0) : null;
    }

    @Override
    public List findAll() {
        return entityManager.createNamedQuery("Student.getAll").getResultList();
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
    public List<Student> findByIds(List<Integer> ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }

        List resultList = entityManager.createNamedQuery("Student.getStudentByIds").setParameter("ids", ids).getResultList();
        return resultList;
    }
}
