package com.fdar.apress.s2.actions.search;

import com.fdar.apress.s2.actions.BaseAction;
import com.fdar.apress.s2.domain.Event;
import com.fdar.apress.s2.services.EventService;

import java.util.List;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public abstract class BaseSearchAction extends BaseAction {

    protected String userInfo;

    protected List<Event> results;
    protected EventService service;

    public void setEventService(EventService service) {
        this.service = service;
    }

    public List<Event> getResults() {
        return results;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public abstract String getEventTemplate();

}
