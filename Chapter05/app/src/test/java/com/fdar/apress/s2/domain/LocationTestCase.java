/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.domain;

public class LocationTestCase extends PersistenceBaseClass {

    public void testAddressCreateFind() throws Exception {

        Address l = new Address();
        l.setName("loc1");
        l.setCity("boston");
        l.setState("ma");
        l.setAddress("newberry st");
        l.setZipcode("02140");

        entityMgr.persist(l);
        entityMgr.flush();

        Address test = entityMgr.find(Address.class,l.getId());
        assertNotNull(test);
        assertEquals(l.getId(),test.getId());
        assertEquals(l.getName(),test.getName());
        assertEquals(l.getCity(),test.getCity());
        assertEquals(l.getState(),test.getState());
        assertEquals(l.getAddress(),test.getAddress());
        assertEquals(l.getZipcode(),test.getZipcode());

    }

    public void testBroadcastCreateFind() throws Exception {

        Broadcast l = new Broadcast();
        l.setName("loc1");
        l.setCity("boston");
        l.setState("ma");
        l.setStationIdentifier("cbs5");
        l.setNetwork("cbs");

        entityMgr.persist(l);
        entityMgr.flush();

        Broadcast test = entityMgr.find(Broadcast.class,l.getId());
        assertNotNull(test);
        assertEquals(l.getId(),test.getId());
        assertEquals(l.getName(),test.getName());
        assertEquals(l.getCity(),test.getCity());
        assertEquals(l.getState(),test.getState());
        assertEquals(l.getStationIdentifier(),test.getStationIdentifier());
        assertEquals(l.getNetwork(),test.getNetwork());

    }
}
