package com.fdar.apress.s2.util;

import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.TextProvider;
import com.fdar.apress.s2.domain.User;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import org.apache.struts2.interceptor.PrincipalAware;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class SecurityInterceptor extends AbstractInterceptor {

    public static final String USER_OBJECT = "user";
    public static final String LOGIN_RESULT = "authenticate";

    public static final String ERROR_MSG_KEY = "msg.pageRequiresRegistration";
    public static final String DEFAULT_MSG = "This page requires registration, please logon or register";

    private List<String> requiresAuthentication;

    public void setRequiresAuthentication( String authenticate ) {
        this.requiresAuthentication = stringToList(authenticate);
    }

    public String intercept(ActionInvocation invocation) throws Exception {

        User user = (User)invocation.getInvocationContext().getSession().get(USER_OBJECT);
        Object action = invocation.getAction();
        boolean annotated = action.getClass().isAnnotationPresent(RequiresAuthentication.class);

        if( user==null &&
                ( annotated || requiresAuthentication(invocation.getProxy().getNamespace()) ) ) {
            if( action instanceof ValidationAware) {
                String msg = action instanceof TextProvider ?
                        ((TextProvider)action).getText(ERROR_MSG_KEY) : DEFAULT_MSG;
                ((ValidationAware)action).addActionError(msg);
            }
            return LOGIN_RESULT;
        }

        return invocation.invoke();
    }

    private List<String> stringToList(String val) {
        if (val != null) {
            String[] list = val.split("[ ]*,[ ]*");
            return Arrays.asList(list);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    private boolean requiresAuthentication( String namespace ) {
        for( String next: requiresAuthentication ) {
            if( namespace.equals(next.trim()) ) {
                return true;
            }
        }
        return false;
    }
}
