package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.Event;

import javax.persistence.*;

public class EventServiceImpl implements EventService {

    private EntityManagerFactory emf;

    public EventServiceImpl() {
        emf = Persistence.createEntityManagerFactory("s2app");
    }

    public void create(Event event) {
        EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            entityMgr.persist(event);

            tx.commit();
        }
        catch (RollbackException e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
    }
}
