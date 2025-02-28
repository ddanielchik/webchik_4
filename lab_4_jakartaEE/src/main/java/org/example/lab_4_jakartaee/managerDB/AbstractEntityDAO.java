package org.example.lab_4_jakartaee.managerDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractEntityDAO<T> {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lapka_4");
    private EntityManager em = emf.createEntityManager();

    public AbstractEntityDAO() {} //консруктор

    public EntityManager getEm() {
        return em;
    }

    public abstract void save(T entity);
}

