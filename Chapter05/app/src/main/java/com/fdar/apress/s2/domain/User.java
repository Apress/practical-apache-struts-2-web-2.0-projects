/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.domain;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table( name="APP_USER", schema="S2APP" )
public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private byte[] portrait;

   @Id @Column(name="EMAIL")
    public String getEmail() {
        return email;
    }

    @EmailValidator(message="Validation Error", key="validate.email")
    public void setEmail(String email) {
        this.email = email;    
    }

    @Column(name="FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    @RequiredStringValidator(message="Validation Error", key="validate.notEmpty", trim=true)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    @RequiredStringValidator(message="Validation Error", key="validate.notEmpty", trim=true)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="USER_PASSWORD")
    public String getPassword() {
        return password;
    }

    @RequiredStringValidator(message="Validation Error", key="validate.notEmpty", trim=true, shortCircuit=true)
    @StringLengthFieldValidator(message = "Length too short", key = "validate.minLength.6", trim=true, minLength="6")
    public void setPassword(String password) {
        this.password = password;
    }

    @Lob @Column(name="PORTRAIT",nullable=true)
    public byte[] getPortrait() {
        return portrait;
    }

    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }
}
