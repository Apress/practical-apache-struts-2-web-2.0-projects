package com.fdar.apress.s2.services;

import com.fdar.apress.s2.domain.Location;
import com.fdar.apress.s2.domain.Address;
import com.fdar.apress.s2.domain.Broadcast;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class LocationDTO {

    private long id;
    private String name;
    private String city;
    private String state;
    private String type;

    private String address;
    private String zipcode;

    private String stationIdentifier;
    private String network;

    public LocationDTO( Location loc ) {

        this.id=loc.getId();
        String cn = loc.getClass().getName();
        this.type=cn.substring(cn.lastIndexOf(".")+1,cn.length());
        this.name=loc.getName();
        this.city=loc.getCity();
        this.state=loc.getState();

        if( loc instanceof Address ) {
            this.address=((Address)loc).getAddress();
            this.zipcode=((Address)loc).getZipcode();
        }

        if( loc instanceof Broadcast ) {
            this.stationIdentifier=((Broadcast)loc).getStationIdentifier();
            this.network=((Broadcast)loc).getNetwork();
        }
    }


    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStationIdentifier() {
        return stationIdentifier;
    }

    public String getNetwork() {
        return network;
    }
}
