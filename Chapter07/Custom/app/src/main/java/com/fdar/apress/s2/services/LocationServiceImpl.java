package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.Location;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

public class LocationServiceImpl implements LocationService {

    private EntityManagerFactory emf;

    public LocationServiceImpl() {
        emf = Persistence.createEntityManagerFactory("s2app");
    }
    public void create(Location loc) {
        EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            entityMgr.persist(loc);

            tx.commit();
        }
        catch (RollbackException e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
    }

    public List<Location> findAll() {
        EntityManager entityMgr = emf.createEntityManager();
        // TODO: needs a polymorphic query across Address and Broadcast
        List<Location> results = new ArrayList<Location>();
        results.addAll(entityMgr.createQuery("from Address").getResultList());
        results.addAll(entityMgr.createQuery("from Broadcast").getResultList());
        return results;
    }


    public Location findById(Long id) {
        EntityManager entityMgr = emf.createEntityManager();
        return entityMgr.find(Location.class,id);
    }
}
