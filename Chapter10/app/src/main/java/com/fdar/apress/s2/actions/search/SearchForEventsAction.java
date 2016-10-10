package com.fdar.apress.s2.actions.search;

import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import com.fdar.apress.s2.domain.Location;

import java.util.List;
import java.util.Arrays;

@ParentPackage("base-package")
@Result(name="success",value= "/WEB-INF/jsp/search/listEventResults.jsp")
public class SearchForEventsAction extends BaseSearchAction {

    private String locationName;
    private String locationCity;
    private String locationState;
    private String contestants;

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public void setContestants(String contestants) {
        this.contestants = contestants;
    }

    public String getEventTemplate() {
        return "apress/extended";
    }

    public String execute() throws Exception {
        results = service.findEventsByExample( createQueryLocation(), createContestantsList() );
        userInfo = getText("searchBy.advanced");
        return SUCCESS;
    }

    private Location createQueryLocation() {
        Location loc = new Location();
        loc.setName(locationName);
        loc.setCity(locationCity);
        loc.setState(locationState);
        return loc;
    }

    private List<String> createContestantsList() {
        if (contestants != null && !"".equals(contestants.trim())) {
            String[] list = contestants.split("[ ]*,[ ]*");
            return Arrays.asList(list);
        } else {
            return null;
        }
    }
}