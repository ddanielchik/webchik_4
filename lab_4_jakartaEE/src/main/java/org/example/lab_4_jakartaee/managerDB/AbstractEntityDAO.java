package org.example.lab_4_jakartaee.managerDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractEntityDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lapka_4");
    private EntityManager em = emf.createEntityManager();

    public AbstractEntityDAO() {} //консруктор
    public abstract void save(Object entity);
    public abstract void find(Object entity);
    public abstract void delete(Object entity);

    public EntityManager getEm() {
        return em;
    }
}

