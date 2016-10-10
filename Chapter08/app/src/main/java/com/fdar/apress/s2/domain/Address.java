package com.fdar.apress.s2.domain;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

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

    @RequiredStringValidator(message="Validation Error", key="validate.notEmpty",trim=true)
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="ZIPCODE")
    public String getZipcode() {
        return zipcode;
    }

    @RequiredStringValidator(message="Validation Error", key="validate.notEmpty",trim=true)
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
