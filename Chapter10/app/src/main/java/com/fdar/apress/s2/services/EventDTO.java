package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.Event;

import java.text.DateFormat;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class EventDTO {

    private long id;
    private String eventName;
    private String startTime;
    private int timeZoneOffset = 0;
    private String votingStartTime;
    private int duration;
    private String lastUpdateTime;
    private LocationDTO location;
    private String status;

    public EventDTO( Event event ) {

        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
        this.id = event.getId();
        this.eventName = event.getName();
        this.startTime = df.format(event.getStartTime());
        this.timeZoneOffset = event.getTimeZoneOffset();
        this.votingStartTime = df.format(event.getVotingStartTime());
        this.duration = event.getDuration();
        this.lastUpdateTime = df.format(event.getLastUpdateTime());
        this.location = new LocationDTO(event.getLocation());
        this.status = event.getStatus().name();
    }

    public long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public String getVotingStartTime() {
        return votingStartTime;
    }

    public int getDuration() {
        return duration;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }
}
