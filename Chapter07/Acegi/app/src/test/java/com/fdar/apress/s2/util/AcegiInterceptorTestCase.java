package com.fdar.apress.s2.util;

import junit.framework.TestCase;
import com.fdar.apress.s2.services.PermissionedUser;
import com.fdar.apress.s2.domain.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.mock.MockActionInvocation;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.TestingAuthenticationToken;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class AcegiInterceptorTestCase extends TestCase {

    public void testIntercept() throws Exception {

        TestAction action = new TestAction();

        AcegiInterceptor interceptor = new AcegiInterceptor();
        MockActionInvocation ai = new MockActionInvocation();
        ai.setAction(action);

        SecurityContextImpl sc = new SecurityContextImpl();
        Authentication auth =
                new TestingAuthenticationToken(
                        new PermissionedUser(new User()),"password",new GrantedAuthority[] {} );
        sc.setAuthentication( auth );
        SecurityContextHolder.setContext(sc);

        assertNull(action.getUser());
        interceptor.intercept(ai);
        assertNotNull(action.getUser());
        assertEquals(auth.getPrincipal(),action.getUser());
    }

    class TestAction {

        private PermissionedUser user;

        public PermissionedUser getUser() {
            return user;
        }

        @AcegiPrincipal
        public void setUser(PermissionedUser user) {
            this.user = user;
        }

        public String execute() {
            return Action.SUCCESS;
        }
    }

}
