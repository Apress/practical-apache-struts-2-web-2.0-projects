package com.fdar.apress.s2.actions.search;

import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;

@ParentPackage("base-package")
@Result(name="success",value= "/WEB-INF/jsp/search/listEventResults.jsp")
public class SearchByTitleAction extends BaseSearchAction {

    private String titlePartial;

    public void setTitlePartial(String titlePartial) {
        this.titlePartial = titlePartial;
    }

    public String getEventTemplate() {
        return "apress";
    }

    public String execute() throws Exception {
        results = service.findByTitle(titlePartial);
        userInfo = "".equals(titlePartial) ?
                getText("searchBy.all") : getText("searchBy.title", new String[]{titlePartial});
        return SUCCESS;
    }
}
