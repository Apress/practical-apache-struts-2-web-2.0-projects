package com.fdar.apress.s2.actions.voting;

import com.fdar.apress.s2.actions.BaseAction;
import com.fdar.apress.s2.services.VotingService;
import com.fdar.apress.s2.util.SecurityInterceptor;
import com.fdar.apress.s2.domain.User;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

import javax.servlet.http.HttpServletRequest;

@ParentPackage("base-package")
@Result(type= ServletActionRedirectResult.class,value="showRecentEvents",params={"namespace","/search"})
public class EnrollAction extends BaseAction implements ServletRequestAware {

    private Long eventId;
    private VotingService votingService;
    private HttpServletRequest request;

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setVotingService(VotingService votingService) {
        this.votingService = votingService;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    public String execute() throws Exception {
        User user = (User)request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
        votingService.enroll( user, eventId );
        return SUCCESS;
    }
}
