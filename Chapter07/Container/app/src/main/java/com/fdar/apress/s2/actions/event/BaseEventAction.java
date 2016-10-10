package com.fdar.apress.s2.actions.event;

import com.fdar.apress.s2.domain.Event;
import com.fdar.apress.s2.actions.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;


public class BaseEventAction extends BaseAction implements ModelDriven<Event> {

    protected Event event = null;

    public Event getModel() {
        return event;
    }

    public void setModel(Event model) {
        this.event = model;
    }

}
