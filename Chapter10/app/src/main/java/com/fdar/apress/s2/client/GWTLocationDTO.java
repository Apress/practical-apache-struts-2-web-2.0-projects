package com.fdar.apress.s2.client;

import java.io.Serializable;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class GWTLocationDTO implements Serializable {

    private long id;
    private String name;
    private String city;
    private String state;
    private String type;

    private String address;
    private String zipcode;

    private String stationIdentifier;
    private String network;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStationIdentifier() {
        return stationIdentifier;
    }

    public void setStationIdentifier(String stationIdentifier) {
        this.stationIdentifier = stationIdentifier;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
}
