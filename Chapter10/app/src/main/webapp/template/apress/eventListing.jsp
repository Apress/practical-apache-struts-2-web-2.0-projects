<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<p>
    <h3><s:property value="parameters.event.name" /></h3>
    <s:text name="display.event.date"/>  <s:date name="parameters.event.startTime" format="MMM d, yyyy"/> <br/>
    <s:text name="display.event.start"/> <s:date name="parameters.event.startTime" format="hh:mm a" /> <br/>
    <s:text name="display.event.voting"/> <s:date name="parameters.event.votingStartTime" format="hh:mm a"/> <br/>
</p>
<p id="eventMessage<s:property value="parameters.event.id"/>">
    <s:if test="#session['user']!=null">
        <s:set name="voterSet" value="parameters.event.voters.{? #this.user.email == #session['user'].email}" />
        <s:if test="#voterSet.size()==0">
            <s:url id="enrollUrl" action="enroll" namespace="/voting" >
                <s:param name="eventId" value="parameters.event.id" />
            </s:url>
            <s:a theme="ajax" notifyTopics="updateResult%{parameters.event.id}" href="%{enrollUrl}" ><s:text name="link.enroll" /></s:a>
        </s:if>
    </s:if>

    <s:url id="resultsUrl" action="findResults" namespace="/voting">
        <s:param name="eventId" value="parameters.event.id" />
    </s:url>
    <s:div theme="ajax" href="%{resultsUrl}"
           executeScripts="true" 
           listenTopics="updateResult%{parameters.event.id}"
           loadingText="%{getText('text.loadingResults')}" />
</p>

    
