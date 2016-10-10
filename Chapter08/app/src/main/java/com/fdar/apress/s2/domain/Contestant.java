package com.fdar.apress.s2.domain;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import javax.persistence.*;

@Entity @Table( name="CONTESTANT", schema="S2APP" )
public class Contestant {

    private long id;
    private String name;
    private String description;
    private Event event;

    @Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="NAME")
    public String getName() {
        return name;
    }

    @RequiredStringValidator(message="Validation Error", key="validate.notEmpty",trim=true)
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @RequiredStringValidator(message="Validation Error", key="validate.notEmpty",trim=true)
    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne @JoinColumn(name="EVENT_FK")
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }


    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object obj) {
        return obj instanceof Contestant && name.equals(((Contestant)obj).getName()); 
    }
}
