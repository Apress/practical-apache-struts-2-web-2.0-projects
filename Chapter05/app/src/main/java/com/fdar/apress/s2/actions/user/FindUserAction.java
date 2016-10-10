/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.actions.user;

import org.apache.struts2.config.Results;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.fdar.apress.s2.domain.User;
import com.fdar.apress.s2.services.UserService;

import javax.servlet.http.HttpServletRequest;

@ParentPackage("base-package")
public class FindUserAction extends BaseUserAction
//        implements ServletRequestAware
{

//    private HttpServletRequest request;
//
//    public void setServletRequest(HttpServletRequest httpServletRequest) {
//        request = httpServletRequest;
//    }


//    @Validations( visitorFields = {
//        @VisitorFieldValidator(fieldName="user", message="Default message", key="i18n.key", shortCircuit=true, appendPrefix=false) }
//    )
    public String execute() throws Exception {
//        if( user!=null ) {
//            request.getSession(true).setAttribute("user",user);
//        }
        return SUCCESS;
    }
}
