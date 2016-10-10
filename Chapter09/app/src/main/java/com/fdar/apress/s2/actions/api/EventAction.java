package com.fdar.apress.s2.actions.api;

import com.fdar.apress.s2.actions.BaseAction;
import com.fdar.apress.s2.domain.Event;
import com.fdar.apress.s2.services.EventService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import java.util.List;


public class EventAction extends BaseAction implements ModelDriven<Event>, Preparable {

    private List<Event> results;
    private EventService service;

    private long id;
    private Event event = new Event();

    public void setEventService(EventService service) {
        this.service = service;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Event getModel() {
        return event;
    }

    public List<Event> getResults() {
        return results;
    }


    public void prepare() throws Exception {
        if(id!=0) {
            event = service.findById(id);
        }
    }

    public String view() {
        return "single";
    }

    public String index() {
        results = service.findAllEvents(10);
        return "list";
    }
}
