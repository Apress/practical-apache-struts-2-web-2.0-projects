package com.fdar.apress.s2.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.fdar.apress.s2.services.PermissionedUser;
import com.fdar.apress.s2.util.AcegiPrincipal;
import org.acegisecurity.Authentication;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class BaseAction extends ActionSupport {

    private PermissionedUser user;

    @AcegiPrincipal
    public void setAuthenticatedUser(PermissionedUser user) {
        this.user = user;
    }

    public PermissionedUser getAuthenticatedUser() {
        return user;
    }
}
