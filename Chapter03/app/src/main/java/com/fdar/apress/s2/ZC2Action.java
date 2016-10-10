/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2;

import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import java.util.Random;

@Results({
    @Result(name="success", value="/jsp/success.jsp", type= ServletDispatcherResult.class),
    @Result(name="input", value="/jsp/input.jsp", type= ServletDispatcherResult.class)
})
public class ZC2Action {

    public String execute() {
        return new Random().nextBoolean() ? "success" : "input";
    }
}
