package com.fdar.apress.s2.actions.event;

import com.fdar.apress.s2.domain.Contestant;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Results;


@ParentPackage("enterEvent")
@Results( value={
    @Result(type= ServletActionRedirectResult.class,value="selectContestants"),
    @Result(name="input",value="/WEB-INF/jsp/event/selectContestants-input.jsp")
})
@Validation
public class RemoveContestantsAction extends BaseEventAction {

    private List<String> selectedContestants;

    public List<String> getSelectedContestants() {
        return selectedContestants;
    }

    public void setSelectedContestants(List<String> selectedContestants) {
        this.selectedContestants = selectedContestants;
    }

    @Validations( expressions = {
        @ExpressionValidator(message="Default message", key="validate.mustSelectOne",
            expression="selectedContestants!=null && selectedContestants.size>0" )
    })
    public String execute() throws Exception {
        List<Contestant> result = new ArrayList<Contestant>();
        for( Contestant c: event.getOptions() ) {
            if( !selectedContestants.contains(c.getName()) ) {
                result.add(c);
            }
        }
        event.setOptions(result);
        return SUCCESS;
    }
}
