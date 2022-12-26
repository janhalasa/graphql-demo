package com.halasa.graphqldemo.repository;

import com.halasa.graphqldemo.entity.PersonEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager em;


    public List<PersonEntity> findAll() {
        Query query = this.em.createQuery("SELECT p FROM Person as p");
        return query.getResultList();
    }

    public PersonEntity persist(PersonEntity person) {
        this.em.persist(person);
        return person;
    }
}
