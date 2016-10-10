package com.fdar.apress.s2.actions.event;

import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import com.fdar.apress.s2.services.LocationService;
import com.fdar.apress.s2.domain.Location;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;

import java.util.List;


@ParentPackage("enterEvent")
@Result(type= ServletActionRedirectResult.class,value="selectContestants")
@Validation
public class SelectLocationAction extends BaseEventAction implements Preparable {

    private List<Location> locations;
    private Long selectedLocation = null;

    private LocationService service;


    public void setLocationService(LocationService service) {
        this.service = service;
    }

    public void prepare() throws Exception {
        locations = service.findAll();
    }


    public List<Location> getLocations() {
        return locations;
    }

    public Long getSelectedLocation() {
        return selectedLocation;
    }

    @RequiredFieldValidator(message = "Default message", key = "validation.selectLocation")
    public void setSelectedLocation(Long selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    public String input() {
        return INPUT;
    }

    public String execute() throws Exception {
        event.setLocation(service.findById(selectedLocation));
        return SUCCESS;
    }
}
