package com.fdar.apress.s2.actions;

import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

import com.fdar.apress.s2.util.RequiresAuthentication;

@RequiresAuthentication
public class LogoffAction extends BaseAction implements ServletRequestAware {

    private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    public String execute() throws Exception {
        request.getSession().invalidate();
        return SUCCESS;
    }
}