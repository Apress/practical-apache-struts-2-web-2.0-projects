package com.fdar.apress.s2.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class BaseAction extends ActionSupport implements PrincipalAware {

    protected PrincipalProxy principal;

    public void setPrincipalProxy(PrincipalProxy principalProxy) {
        this.principal = principalProxy;
    }

    public PrincipalProxy getPrincipal() {
        return principal;
    }
}
