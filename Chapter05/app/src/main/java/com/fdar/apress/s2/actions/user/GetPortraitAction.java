/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.actions.user;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Results;
import org.apache.struts2.config.Result;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import org.apache.struts2.dispatcher.StreamResult;
import com.fdar.apress.s2.actions.Utils;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

@ParentPackage("base-package")
@Result(name="success", value="inputStream", type=StreamResult.class)
public class GetPortraitAction extends BaseUserAction {

    public InputStream getInputStream() {
        if( user==null || user.getPortrait()==null) {
            return Utils.getNoImageInputStream(getText("text.noImage"));
        } else {
            return new ByteArrayInputStream(user.getPortrait());
        }
    }

}
