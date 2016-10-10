package com.fdar.apress.s2.actions.voting;

import com.fdar.apress.s2.actions.BaseAction;
import com.fdar.apress.s2.services.VotingService;
import com.fdar.apress.s2.services.VotingResult;
import com.fdar.apress.s2.services.EventService;
import com.fdar.apress.s2.domain.Contestant;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;

import java.util.List;
import java.util.ArrayList;

@ParentPackage("base-package")
@Result(value="/template/apress/eventResults.jsp")
public class FindResultsAction extends BaseAction {

    private Long eventId;
    private VotingService votingService;
    private EventService service;
    private List<VotingResult> results;

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setVotingService(VotingService votingService) {
        this.votingService = votingService;
    }

    public void setEventService(EventService service) {
        this.service = service;
    }

    public List<VotingResult> getResults() {
        return results;
    }

    public String execute() throws Exception {

        results = new ArrayList<VotingResult>();
        for( Contestant next : service.findById(eventId).getOptions() ) {
            results.add( new VotingResult(next, votingService.getResults(eventId,next.getId()) ) );  
        }
        return SUCCESS;
    }

}
