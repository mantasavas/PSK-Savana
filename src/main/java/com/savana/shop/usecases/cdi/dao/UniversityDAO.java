package com.savana.shop.usecases.cdi.dao;

import com.savana.shop.entities.University;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class UniversityDAO {
    @Inject
    private EntityManager em;

    public void create(University university) {
        em.persist(university);
    }

    public List<University> getAllUniversities() {
        return em.createNamedQuery("University.findAll", University.class).getResultList();
    }
}
