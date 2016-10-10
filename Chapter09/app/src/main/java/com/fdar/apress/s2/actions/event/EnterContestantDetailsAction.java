package com.fdar.apress.s2.actions.event;

import com.fdar.apress.s2.domain.Contestant;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;


@ParentPackage("enterEvent")
@Result(type= ServletActionRedirectResult.class,value="selectContestants")
@Validation
public class EnterContestantDetailsAction extends BaseEventAction {

    private Contestant contestant = new Contestant();

    @VisitorFieldValidator(message="", fieldName="contestant", appendPrefix=true)
    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public String input() {
        return INPUT;
    }

    public String execute() throws Exception {
        event.addOption(contestant);
        return SUCCESS;
    }
}
