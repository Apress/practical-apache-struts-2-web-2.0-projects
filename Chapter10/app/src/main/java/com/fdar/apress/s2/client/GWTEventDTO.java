package com.fdar.apress.s2.client;

import java.io.Serializable;

public class GWTEventDTO implements Serializable {

    private long id;
    private String eventName;
    private String startTime;
    private int timeZoneOffset = 0;
    private String votingStartTime;
    private int duration;
    private String lastUpdateTime;
    private GWTLocationDTO location;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public void setTimeZoneOffset(int timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }

    public String getVotingStartTime() {
        return votingStartTime;
    }

    public void setVotingStartTime(String votingStartTime) {
        this.votingStartTime = votingStartTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public GWTLocationDTO getLocation() {
        return location;
    }

    public void setLocation(GWTLocationDTO location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
