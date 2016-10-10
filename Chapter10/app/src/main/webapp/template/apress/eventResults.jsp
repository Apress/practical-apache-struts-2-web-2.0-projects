<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<p>
    <h5><s:text name="text.results" /></h5>

    <p>
        <s:set name="myVotes" value="results[0].contestant.event.voters.{? #this.user.email == #session['user'].email}" />
        <s:set name="canVote" value="#myVotes!=null && #myVotes.get(0).voteRecordedTime==null" />
        <s:if test="canVote">
            <s:text name="text.canVote" /><br/>
        </s:if>
        <s:iterator value="results">
            ( <s:property value="numberOfVotes" /> ) <s:property value="contestant.name" />
            <s:if test="canVote" >
                <s:url id="voteUrl" action="vote" namespace="/voting">
                    <s:param name="eventId" value="contestant.event.id" />
                    <s:param name="contestantId" value="contestant.id" />
                </s:url>
                [<s:a theme="ajax" href="%{voteUrl}" notifyTopics="updateResult%{results[0].contestant.event.id}"><s:text name="link.vote" /></s:a>]
            </s:if>
            <br/>
        </s:iterator>
    </p>
</p>

<script type="text/javascript">
    <s:if test="#myVotes!=null && #myVotes.size()>0">
        <s:if test="#myVotes.get(0).voteRecordedTime!=null">
            displayText = '<s:text name="text.thanksForVoting" />';
        </s:if>
        <s:else>
            displayText = '';
        </s:else>
        dojo.byId("eventMessage<s:property value="results[0].contestant.event.id"/>").innerHTML = displayText;
    </s:if>
</script>

