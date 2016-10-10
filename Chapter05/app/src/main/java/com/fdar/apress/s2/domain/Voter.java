/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity @Table( name="VOTER", schema="S2APP" )
public class Voter implements Serializable {

    private long id;
    private Date enrollmentTime;
    private Date voteRecordedTime;
    private Contestant votedForOption;
    private User user;
    private Event event;

    @Id  @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="ENROLLMENT_TIME") @Temporal(TemporalType.TIMESTAMP)
    public Date getEnrollmentTime() {
        return enrollmentTime;
    }

    public void setEnrollmentTime(Date enrollmentTime) {
        this.enrollmentTime = enrollmentTime;
    }

    @Column(name="VOTED_ON",nullable=true) @Temporal(TemporalType.TIMESTAMP)
    public Date getVoteRecordedTime() {
        return voteRecordedTime;
    }

    public void setVoteRecordedTime(Date voteRecordedTime) {
        this.voteRecordedTime = voteRecordedTime;
    }

    @ManyToOne @JoinColumn(name="CONTESTANT_FK",nullable=true)
    public Contestant getVotedForOption() {
        return votedForOption;
    }

    public void setVotedForOption(Contestant votedForOption) {
        this.votedForOption = votedForOption;
    }

    @ManyToOne @JoinColumn(name="USER_FK")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne @JoinColumn(name="EVENT_FK")
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
