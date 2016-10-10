package com.fdar.apress.s2.actions.event;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.CustomValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.fdar.apress.s2.domain.Event;
import com.fdar.apress.s2.domain.Progress;
import com.fdar.apress.s2.util.TimeUtil;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;


@ParentPackage("enterEvent")
@Result(type= ServletActionRedirectResult.class,value="selectLocation",params={"method","input"})
@Validation
public class EnterEventDetailsAction extends BaseEventAction implements Preparable {

    private Date partialStartDate;
    private String patialStartTime;
    private String partialVotingStartTime;

    public void prepare() throws Exception {
        event = new Event();
        event.setStatus(Progress.NOT_STARTED);
        event.setLastUpdateTime( Calendar.getInstance().getTime() );
    }

    public Date getPartialStartDate() {
        return partialStartDate;
    }

    @RequiredFieldValidator(message="Validation Error", key="validate.notEmpty")
    public void setPartialStartDate(Date partialStartDate) {
        this.partialStartDate = partialStartDate;
    }

    public String getPatialStartTime() {
        return patialStartTime;
    }

    @CustomValidator(type ="timeValidator", key="validate.timeOfDay")
    public void setPatialStartTime(String patialStartTime) {
        this.patialStartTime = patialStartTime;
    }

    public String getPartialVotingStartTime() {
        return partialVotingStartTime;
    }

    @CustomValidator(type ="timeValidator", key="validate.timeOfDay")
    public void setPartialVotingStartTime(String partialVotingStartTime) {
        this.partialVotingStartTime = partialVotingStartTime;
    }

    @VisitorFieldValidator(message="Default message", fieldName="model", shortCircuit=false, appendPrefix=false)
    public String execute() throws Exception {

        Calendar cal = Calendar.getInstance();

        cal.setTime(partialStartDate);
        TimeUtil timeUtil = new TimeUtil(patialStartTime);
        event.setStartTime( timeUtil.resolveDate( partialStartDate, event.getTimeZoneOffset() ) );

        cal = Calendar.getInstance();
        cal.setTime(partialStartDate);
        timeUtil = new TimeUtil(partialVotingStartTime);
        event.setVotingStartTime( timeUtil.resolveDate( partialStartDate, event.getTimeZoneOffset() ) );

        return SUCCESS;
    }
}