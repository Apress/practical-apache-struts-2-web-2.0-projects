package com.fdar.apress.s2.actions.event;

import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.fdar.apress.s2.services.EventService;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;


@ParentPackage("enterEvent")
@Results( value={
    @Result(name="input",value="/WEB-INF/jsp/event/selectContestants-input.jsp"),
    @Result(type= ServletActionRedirectResult.class,value="completeEvent")
})
@Validation
public class SaveEventAction extends BaseEventAction {

    private EventService service;

    public void setEventService(EventService service) {
        this.service = service;
    }

    @Validations( expressions={
        @ExpressionValidator(message="Default message", key="validate.moreThanTwoOptions",
            expression="options!=null && options.size>1" )
    })
    public String execute() throws Exception {
        service.create(event);
        return SUCCESS;
    }
}
