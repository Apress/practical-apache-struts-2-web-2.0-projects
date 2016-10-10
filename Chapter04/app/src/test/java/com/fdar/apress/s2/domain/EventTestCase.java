/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.domain;

import java.util.Date;

public class EventTestCase extends PersistenceBaseClass {

    public void testCreateFindNoVoters() throws Exception {

        Date now = new Date(System.currentTimeMillis());
        Event e = new Event();

        e.setName("event");
        e.setDuration(3);
        e.setLastUpdateTime(now);
        e.setTimeZoneOffset(-5);
        e.setStatus(Progress.NOT_STARTED);
        e.setStartTime(now);
        e.setVotingStartTime(now);

        Address l = new Address();
        l.setName("loc1");
        l.setCity("boston");
        l.setState("ma");
        l.setAddress("newberry st");
        l.setZipcode("02140");
        e.setLocation(l);

        Contestant c1 = new Contestant();
        c1.setName("c1");
        c1.setDescription("desc1");
        e.addOption(c1);

        Contestant c2 = new Contestant();
        c2.setName("c2");
        c2.setDescription("desc2");
        e.addOption(c2);

        entityMgr.persist(e);
        entityMgr.flush();

        Event test = entityMgr.find(Event.class,e.getId());
        assertNotNull(test);
        assertEquals(e.getId(),test.getId());
        assertEquals(e.getName(),test.getName());
        assertEquals(e.getDuration(),test.getDuration());
        assertEquals(e.getLastUpdateTime(),test.getLastUpdateTime());
        assertEquals(e.getTimeZoneOffset(),test.getTimeZoneOffset());
        assertEquals(e.getStatus(),test.getStatus());
        assertEquals(e.getStartTime(),test.getStartTime());
        assertEquals(e.getVotingStartTime(),test.getVotingStartTime());

        assertNotNull(test.getLocation());
        assertEquals(l.getId(),test.getLocation().getId());
        assertEquals(l.getName(),test.getLocation().getName());
        assertEquals(l.getCity(),test.getLocation().getCity());
        assertEquals(l.getState(),test.getLocation().getState());
        assertEquals(l.getAddress(),((Address)test.getLocation()).getAddress());
        assertEquals(l.getZipcode(),((Address)test.getLocation()).getZipcode());

        assertNotNull(test.getOptions());
        assertEquals(2,test.getOptions().size());

        Contestant ct1 = c1.getId()==test.getOptions().get(0).getId()
                ? test.getOptions().get(0) : test.getOptions().get(1);
        assertEquals(c1.getId(),ct1.getId());
        assertEquals(c1.getName(),ct1.getName());
        assertEquals(c1.getDescription(),ct1.getDescription());
        assertEquals(e.getId(),ct1.getEvent().getId());

        Contestant ct2 = c2.getId()==test.getOptions().get(0).getId()
                ? test.getOptions().get(0) : test.getOptions().get(1);
        assertEquals(c2.getId(),ct2.getId());
        assertEquals(c2.getName(),ct2.getName());
        assertEquals(c2.getDescription(),ct2.getDescription());
        assertEquals(e.getId(),ct2.getEvent().getId());

    }

    public void testCreateFind() throws Exception {

        Address l = new Address();
        l.setName("loc1");
        l.setCity("boston");
        l.setState("ma");
        l.setAddress("newberry st");
        l.setZipcode("02140");

        entityMgr.persist(l);
        entityMgr.flush();

        Address loc = entityMgr.find(Address.class,l.getId());

        Date now = new Date(System.currentTimeMillis());
        Event e = new Event();

        e.setName("event");
        e.setDuration(3);
        e.setLastUpdateTime(now);
        e.setTimeZoneOffset(-5);
        e.setStatus(Progress.NOT_STARTED);
        e.setStartTime(now);
        e.setVotingStartTime(now);

        e.setLocation(loc);

        Contestant c1 = new Contestant();
        c1.setName("c1");
        c1.setDescription("desc1");
        e.addOption(c1);

        Contestant c2 = new Contestant();
        c2.setName("c2");
        c2.setDescription("desc2");
        e.addOption(c2);

        entityMgr.persist(e);
        entityMgr.flush();


        // add the voters

        User u1 = new User();
        u1.setEmail("test@test.com");
        u1.setFirstName("mr");
        u1.setLastName("test");
        u1.setPassword("pw");
        entityMgr.persist(u1);

        User u2 = new User();
        u2.setEmail("test2@test.com");
        u2.setFirstName("mr2");
        u2.setLastName("test2");
        u2.setPassword("pw2");
        entityMgr.persist(u2);
        entityMgr.flush();

        // voted
        Voter v1 = new Voter();
        v1.setUser(u1);
        v1.setEnrollmentTime(now);
        v1.setVotedForOption(c2);
        v1.setVoteRecordedTime(now);
        e.addVoter(v1);

        // not voted yet
        Voter v2 = new Voter();
        v2.setUser(u2);
        v2.setEnrollmentTime(now);
        e.addVoter(v2);

        entityMgr.persist(e);


        // test

        Event test = entityMgr.find(Event.class,e.getId());
        assertNotNull(test);
        assertEquals(e.getId(),test.getId());
        assertEquals(e.getName(),test.getName());
        assertEquals(e.getDuration(),test.getDuration());
        assertEquals(e.getLastUpdateTime(),test.getLastUpdateTime());
        assertEquals(e.getTimeZoneOffset(),test.getTimeZoneOffset());
        assertEquals(e.getStatus(),test.getStatus());
        assertEquals(e.getStartTime(),test.getStartTime());
        assertEquals(e.getVotingStartTime(),test.getVotingStartTime());

        assertNotNull(test.getLocation());
        assertEquals(l.getId(),test.getLocation().getId());
        assertEquals(l.getName(),test.getLocation().getName());
        assertEquals(l.getCity(),test.getLocation().getCity());
        assertEquals(l.getState(),test.getLocation().getState());
        assertEquals(l.getAddress(),((Address)test.getLocation()).getAddress());
        assertEquals(l.getZipcode(),((Address)test.getLocation()).getZipcode());

        assertNotNull(test.getOptions());
        assertEquals(2,test.getOptions().size());

        Contestant ct1 = c1.getId()==test.getOptions().get(0).getId()
                ? test.getOptions().get(0) : test.getOptions().get(1);
        assertEquals(c1.getId(),ct1.getId());
        assertEquals(c1.getName(),ct1.getName());
        assertEquals(c1.getDescription(),ct1.getDescription());
        assertEquals(e.getId(),ct1.getEvent().getId());

        Contestant ct2 = c2.getId()==test.getOptions().get(0).getId()
                ? test.getOptions().get(0) : test.getOptions().get(1);
        assertEquals(c2.getId(),ct2.getId());
        assertEquals(c2.getName(),ct2.getName());
        assertEquals(c2.getDescription(),ct2.getDescription());
        assertEquals(e.getId(),ct2.getEvent().getId());

        assertNotNull(test.getVoters());
        assertEquals(2,test.getVoters().size());
        Object[] voters = test.getVoters().toArray();

        Voter vt1 = v1.getId()==((Voter)voters[0]).getId() ? (Voter)voters[0] : (Voter)voters[1];
        assertEquals(v1.getId(),vt1.getId());
        assertEquals(v1.getEnrollmentTime(),vt1.getEnrollmentTime());
        assertNotNull(vt1.getEvent());
        assertEquals(e.getId(),vt1.getEvent().getId());
        assertEquals(v1.getVotedForOption(),vt1.getVotedForOption());
        assertEquals(v1.getVoteRecordedTime(),vt1.getVoteRecordedTime());
        assertNotNull(vt1.getUser());
        assertEquals(u1.getEmail(),vt1.getUser().getEmail());
        assertEquals(u1.getFirstName(),vt1.getUser().getFirstName());
        assertEquals(u1.getLastName(),vt1.getUser().getLastName());
        assertEquals(u1.getPassword(),vt1.getUser().getPassword());

        Voter vt2 = v2.getId()==((Voter)voters[0]).getId() ? (Voter)voters[0] : (Voter)voters[1];
        assertEquals(v2.getId(),vt2.getId());
        assertEquals(v2.getEnrollmentTime(),vt2.getEnrollmentTime());
        assertNotNull(vt2.getEvent());
        assertEquals(e.getId(),vt2.getEvent().getId());
        assertNull(vt2.getVotedForOption());
        assertNull(vt2.getVoteRecordedTime());
        assertNotNull(vt2.getUser());
        assertEquals(u2.getEmail(),vt2.getUser().getEmail());
        assertEquals(u2.getFirstName(),vt2.getUser().getFirstName());
        assertEquals(u2.getLastName(),vt2.getUser().getLastName());
        assertEquals(u2.getPassword(),vt2.getUser().getPassword());

    }
}
