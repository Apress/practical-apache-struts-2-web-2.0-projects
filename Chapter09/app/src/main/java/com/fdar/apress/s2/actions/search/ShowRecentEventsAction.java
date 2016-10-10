package com.fdar.apress.s2.actions.search;

import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import com.fdar.apress.s2.services.EventService;
import com.fdar.apress.s2.actions.BaseAction;
import com.fdar.apress.s2.domain.Event;

import java.util.List;

@ParentPackage("base-package")
@Result(name="success",value= "/WEB-INF/jsp/search/listEvents-partial.jsp")
public class ShowRecentEventsAction extends BaseAction {

    private int number;
    private List<Event> results;
    private EventService service;

    public void setEventService(EventService service) {
        this.service = service;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Event> getResults() {
        return results;
    }

    public String execute() throws Exception {
        results = service.findAllEvents(number);
        return SUCCESS;
    }
}