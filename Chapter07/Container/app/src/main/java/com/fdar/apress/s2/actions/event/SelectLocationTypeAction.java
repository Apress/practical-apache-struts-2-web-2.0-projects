package com.fdar.apress.s2.actions.event;

import com.opensymphony.xwork2.Preparable;
import com.fdar.apress.s2.domain.Broadcast;
import com.fdar.apress.s2.domain.Location;
import com.fdar.apress.s2.domain.Address;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

import java.util.List;
import java.util.ArrayList;


@ParentPackage("enterEvent")
@Result(type= ServletActionRedirectResult.class,value="enterLocationDetails")
public class SelectLocationTypeAction extends BaseEventAction implements Preparable {

    private Integer typeId;
    private List<Location> types;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<Location> getTypes() {
        return types;
    }

    public void prepare() throws Exception {
        types = new ArrayList<Location>();
        types.add(new Address());
        types.add(new Broadcast());
    }

    public String input() throws Exception {
        return INPUT;
    }

}
