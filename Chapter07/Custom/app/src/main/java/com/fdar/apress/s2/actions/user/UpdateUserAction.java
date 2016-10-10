package com.fdar.apress.s2.actions.user;

import org.apache.struts2.config.Results;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import com.fdar.apress.s2.actions.Utils;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@ParentPackage("base-package")
@Results({
    @Result(name="success", value="index", type= ServletActionRedirectResult.class),
    @Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/user/findUser-success.jsp"),
    @Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/user/findUser-success.jsp")
})
@Validation
public class UpdateUserAction extends BaseUserAction {

    @Validations( visitorFields = {
            @VisitorFieldValidator(
                    message = "Default message", 
                    fieldName= "model", appendPrefix = false) }
    )
    public String execute() throws Exception {
        if(uploadFileName!=null && !"".equals(uploadFileName)) {
            user.setPortrait(Utils.getBytesFromFile(upload));
        }
        service.persist(user,emailId);
        return SUCCESS;
    }
}
