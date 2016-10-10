/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.actions.user;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;
import org.jmock.core.Constraint;
import com.fdar.apress.s2.services.UserService;
import com.fdar.apress.s2.domain.User;
import com.opensymphony.xwork2.Action;

public class UpdateUserTestCase extends MockObjectTestCase {

    public UpdateUserTestCase() {
        super();
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPersistNewUser() throws Exception {

        Mock service = new Mock(UserService.class);
        service.expects(once()).method("persist")
                .with(isA(User.class),eq(null));

        UpdateUserAction action = new UpdateUserAction();
        action.setUserService((UserService)service.proxy());

        action.prepare();
        assertEquals(Action.SUCCESS,action.execute());
        service.verify();
    }

    public void testPersistExistingUser() throws Exception {

        User user = new User();

        Mock service = new Mock(UserService.class);
        service.expects(once()).method("findByEmail")
                .with(eq("bob@test.com"))
                .will(returnValue(user));
        service.expects(once()).method("persist")
                .with(same(user),eq("bob@test.com"));

        UpdateUserAction action = new UpdateUserAction();
        action.setUserService((UserService)service.proxy());
        action.setEmailId("bob@test.com");

        action.prepare();
        assertEquals(Action.SUCCESS,action.execute());
        service.verify();
    }

}
