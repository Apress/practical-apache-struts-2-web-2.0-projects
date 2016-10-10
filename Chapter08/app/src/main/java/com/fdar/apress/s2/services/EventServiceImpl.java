package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.Event;
import com.fdar.apress.s2.domain.Location;

import javax.persistence.*;
import java.util.List;

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

    public List<Event> findAllEvents(int max) {
        EntityManager entityMgr = emf.createEntityManager();
        return entityMgr
                .createQuery("from Event e order by e.startTime desc")
                .setMaxResults(max)
                .getResultList();
    }

    public List<Event> findByTitle( String titlePartial ) {
        EntityManager entityMgr = emf.createEntityManager();
        return entityMgr
                .createQuery("from Event e where e.name like :title order by e.startTime desc")
                .setParameter("title","%"+titlePartial+"%")
                .getResultList();
    }

    public List<Event> findEventsByExample(Location location, List<String> contestantsList) {
        return findAllEvents(10);
    }

}
