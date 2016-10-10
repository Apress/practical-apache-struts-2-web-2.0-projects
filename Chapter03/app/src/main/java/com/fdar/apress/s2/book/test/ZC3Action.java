/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.book.test;

import org.apache.struts2.config.Result;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

@Result(name="success", value="/jsp/success.jsp", type= ServletDispatcherResult.class)
@Namespace("/testing")
@ParentPackage("struts-default")
public class ZC3Action {

	public String execute() {
		return "success";
	}

}
