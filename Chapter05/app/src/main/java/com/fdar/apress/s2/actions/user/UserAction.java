/**
 * Copyright:	Copyright (c) From Down & Around, Inc.
 */

package com.fdar.apress.s2.actions.user;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.fdar.apress.s2.domain.User;
import com.fdar.apress.s2.services.UserService;
import com.fdar.apress.s2.actions.Utils;
import com.fdar.apress.s2.actions.BaseAction;

import java.io.File;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

@Validation
public class UserAction extends BaseAction implements ModelDriven<User>, Preparable {

    private User user;
    private String emailId;
    private UserService service;

    private File upload;
    private String uploadFileName;

    public User getModel() {
        return user;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String fileName) {
        this.uploadFileName = fileName;
    }

    public void setUserService(UserService service) {
        this.service = service;
    }

    public void prepare() throws Exception {
        if( emailId==null || "".equals(emailId) ) {
            user = new User();
        } else {
            user = service.findByEmail(emailId);
        }
    }

    public String find() {
        return INPUT;
    }

    @Validations( visitorFields = {
            @VisitorFieldValidator(
                    message = "Default message", key = "i18n.key",
                    fieldName= "model", appendPrefix = false) }
    )
    public String update() {
        if(uploadFileName!=null && !"".equals(uploadFileName)) {
            user.setPortrait(Utils.getBytesFromFile(upload));
        }
        service.persist(user,emailId);
        return SUCCESS;
    }

    public InputStream getInputStream() {
        if( user==null || user.getPortrait()==null) {
            return Utils.getNoImageInputStream(getText("text.noImage"));
        } else {
            return new ByteArrayInputStream(user.getPortrait());
        }
    }

}
