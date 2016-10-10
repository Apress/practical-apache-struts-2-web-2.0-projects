/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.domain;

import javax.persistence.*;

@Entity @Table( name="ADDRESS", schema="S2APP" )
@PrimaryKeyJoinColumn(name="LOCATION_PK")
public class Address extends Location {

    private String address;
    private String zipcode;

    @Column(name="ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="ZIPCODE")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
