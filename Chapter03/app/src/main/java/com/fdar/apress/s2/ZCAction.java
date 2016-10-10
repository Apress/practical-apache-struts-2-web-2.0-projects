package com.fdar.apress.s2;

import org.apache.struts2.config.Result;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

@Result(name="success", value="/jsp/success.jsp", type= ServletDispatcherResult.class)
public class ZCAction {

	public String execute() {
		return "success";
	}

}