package com.halasa.graphqldemo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CountryRepository {

    @PersistenceContext
    private EntityManager em;
}
