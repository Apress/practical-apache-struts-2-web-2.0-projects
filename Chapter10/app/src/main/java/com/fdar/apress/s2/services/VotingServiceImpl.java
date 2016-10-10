package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class VotingServiceImpl implements VotingService {

    private EntityManagerFactory emf;

    public VotingServiceImpl() {
        emf = Persistence.createEntityManagerFactory("s2app");
    }

    public void enroll(User user, Long eventId) {

        EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            Event event = entityMgr.find(Event.class,eventId);

            Voter voter = new Voter();
            Calendar cal = Calendar.getInstance();
            voter.setEnrollmentTime(cal.getTime());
            voter.setEvent(event);
            voter.setUser(user);
            entityMgr.persist(voter);

            event.addVoter(voter);
            entityMgr.persist(event);

            tx.commit();

        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
    }


    public void vote(User user, Long eventId, Long contestantId) {

        EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            Event event = entityMgr.find(Event.class,eventId);
            Contestant contentant = entityMgr.find(Contestant.class,contestantId);
            Calendar cal = Calendar.getInstance();

            Voter voter = null;
            for( Voter next: event.getVoters() ) {
                if( user.getEmail().equals(next.getUser().getEmail()) ) {
                    next.setVotedForOption(contentant);
                    next.setVoteRecordedTime(cal.getTime());
                    entityMgr.persist(next);
                }
            }

            tx.commit();

        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
    }


    public Long getResults(Long eventId, Long contestantId) {

        EntityManager entityMgr = emf.createEntityManager();
        return (Long)entityMgr.createQuery("select count(v) from Voter v where v.event.id=:eventId and v.votedForOption.id=:contestantId")
                .setParameter("eventId",eventId)
                .setParameter("contestantId",contestantId)
                .getSingleResult();
    }
}
