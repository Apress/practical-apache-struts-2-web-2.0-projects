package com.fdar.apress.s2.actions.event;

import org.apache.struts2.config.Results;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

import java.util.List;
import java.util.ArrayList;

import com.opensymphony.webwork.dispatcher.FlashResult;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.validators.VisitorFieldValidator;
import com.opensymphony.xwork2.validator.DelegatingValidatorContext;
import com.fdar.apress.s2.services.LocationService;
import com.fdar.apress.s2.domain.Broadcast;
import com.fdar.apress.s2.domain.Address;
import com.fdar.apress.s2.domain.Location;

@ParentPackage("enterEvent")
@Results( value={
    @Result(type= ServletActionRedirectResult.class,value="selectLocation",params={"method","input"}),
    @Result(type= FlashResult.class,name="selectType",value="/event/flashedSelectEventType.action")
})
public class EnterLocationDetailsAction extends BaseEventAction implements Preparable {

    private String setup;
    private String typeClass;
    private Object location;

    private LocationService service;

    public void prepare() throws Exception {
        if( typeClass!=null ) {
            Class clazz = Class.forName(typeClass);
            location = clazz.newInstance();
        }
    }

    public void setLocationService(LocationService service) {
        this.service = service;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    public Broadcast getBroadcast() {
        return (Broadcast)location;
    }

    public void setBroadcast( Broadcast location ) {
        this.location = location;
    }

    public Address getAddress() {
        return (Address)location;
    }

    public void setAddress( Address location ) {
        this.location = location;
    }

    public String execute() throws Exception {

        if( typeClass==null ) {
            addFieldError("typeClass",getText("validate.selectType",new String[]{}));
            return "selectType";

        } else {

            String objType = typeClass.substring(typeClass.lastIndexOf(".")+1).toLowerCase();

            if( setup!=null ) {
                return objType;
                
            } else {

                VisitorFieldValidator validator = new VisitorFieldValidator();
                validator.setAppendPrefix(true);
                validator.setValidatorContext(new DelegatingValidatorContext(this));
                validator.setFieldName(objType);
                validator.validate(this);

                if( hasFieldErrors() ) {
                    return objType;
                }
            }

            service.create((Location)location);
            return SUCCESS;
        }
    }
}
