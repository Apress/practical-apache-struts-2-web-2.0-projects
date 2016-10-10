/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.domain;

public class UserTestCase extends PersistenceBaseClass {

    public void testCreateFind() throws Exception {

        User u = new User();
        u.setEmail("test@test.com");
        u.setFirstName("mr");
        u.setLastName("test");
        u.setPassword("pw");
        entityMgr.persist(u);
        entityMgr.flush();

        User test = entityMgr.find(User.class,u.getEmail());
        assertNotNull(test);
        assertEquals(u.getEmail(),test.getEmail());
        assertEquals(u.getFirstName(),test.getFirstName());
        assertEquals(u.getLastName(),test.getLastName());
        assertEquals(u.getPassword(),test.getPassword());

    }
}
