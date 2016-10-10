package com.fdar.apress.s2.domain;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import javax.persistence.*;

@Entity @Table( name="BROADCAST", schema="S2APP" )
@PrimaryKeyJoinColumn(name="LOCATION_PK")
public class Broadcast extends Location {

    private String stationIdentifier;
    private String network;

    @Column(name="CALLSIGN")
    public String getStationIdentifier() {
        return stationIdentifier;
    }

    @RequiredStringValidator(message="Validation Error", key="validate.notEmpty",trim=true)
    public void setStationIdentifier(String stationIdentifier) {
        this.stationIdentifier = stationIdentifier;
    }

    @Column(name="NETWORK")
    public String getNetwork() {
        return network;
    }

    @RequiredStringValidator(message="Validation Error", key="validate.notEmpty",trim=true)
    public void setNetwork(String network) {
        this.network = network;
    }
}
