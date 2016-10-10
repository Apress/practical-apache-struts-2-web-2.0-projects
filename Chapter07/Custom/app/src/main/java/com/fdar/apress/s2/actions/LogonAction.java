package com.fdar.apress.s2.actions;

import com.fdar.apress.s2.services.UserService;
import com.fdar.apress.s2.util.SecurityInterceptor;
import com.fdar.apress.s2.domain.User;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class LogonAction extends BaseAction implements ServletRequestAware {

    private String username;
    private String password;

    protected UserService service;
    private HttpServletRequest request;

    public static final String FAILURE = "failed";

    public void setUserService(UserService service) {
        this.service = service;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() throws Exception {
        User user = service.findByEmail(username);
        if( user!=null && null!=username && !"".equals(username)
                && password.equals(user.getPassword()) ) {
            request.getSession(true).setAttribute(SecurityInterceptor.USER_OBJECT,user);
            return SUCCESS;
        } else {
            addActionError(getText("auth.failed"));
            return FAILURE;
        }
    }
}
