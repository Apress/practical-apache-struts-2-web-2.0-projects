package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.Event;
import com.fdar.apress.s2.domain.Location;

import java.util.List;
import java.util.Date;

public interface EventService {

    public Event findById( long id );

    public void create(Event event);

    public List<Event> findAllEvents(int max);

    public List<Event> findByTitle( String titlePartial );

    public List<Event> findEventsByExample(Location queryLocation, List<String> contestantsList);
    
    public List<Event> findAfterStartingTime( Date startingTime );
}
