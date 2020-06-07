package com.project.service;

import com.project.dao.PersonDao;
import com.project.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    
    @Autowired
    private PersonDao personDao;

    public Person findAllByEmail(String email) {
        return (Person) personDao.findAllByEmail(email);
    }

    public List findAll() {
        return personDao.findAll();
    }

    public void update(Person person) {
        personDao.update(person);
    }

    public void save(Person person) {
        personDao.persist(person);
    }

    public Person findById(int id) {
        return (Person) this.personDao.findById(id);
    }

    public void remove(Person person){
        personDao.remove(person);
    }

    public List findAllByActive(Boolean active) {
        return personDao.findAllByActive(active);
    }

}
