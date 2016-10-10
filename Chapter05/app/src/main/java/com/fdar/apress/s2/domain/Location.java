/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table( name="LOCATION", schema="S2APP" )
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Location implements Serializable {

    private long id;
    private String name;
    private String city;
    private String state;

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

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
