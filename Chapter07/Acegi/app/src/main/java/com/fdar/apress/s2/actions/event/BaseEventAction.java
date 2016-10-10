package com.fdar.apress.s2.actions.event;

import com.fdar.apress.s2.domain.Event;
import com.fdar.apress.s2.actions.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;


public class BaseEventAction extends BaseAction implements ModelDriven<Event>, PrincipalAware {

    protected Event event = null;
    protected PrincipalProxy principal;

    public void setPrincipalProxy(PrincipalProxy principalProxy) {
        this.principal = principalProxy;
    }

    public PrincipalProxy getPrincipal() {
        return principal;
    }

    public Event getModel() {
        return event;
    }

    public void setModel(Event model) {
        this.event = model;
    }

}
