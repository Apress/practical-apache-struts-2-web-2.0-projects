package com.fdar.apress.s2.actions.user;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ActionSupport;
import com.fdar.apress.s2.domain.User;
import com.fdar.apress.s2.services.UserService;
import com.fdar.apress.s2.actions.BaseAction;

import java.io.File;

public abstract class BaseUserAction extends BaseAction implements ModelDriven<User>, Preparable {

    protected User user;
    protected String emailId;
    protected UserService service;

    protected File upload;
    protected String uploadFileName;
    
    public User getModel() {
        return user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

}
