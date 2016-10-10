<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<p>
<h3><s:property value="parameters.event.name" /></h3>
<s:text name="display.event.date"/>  <s:date name="parameters.event.startTime" format="MMM d, yyyy"/> <br/>
<s:text name="display.event.start"/> <s:date name="parameters.event.startTime" format="hh:mm a" /> <br/>
<s:text name="display.event.voting"/> <s:date name="parameters.event.votingStartTime" format="hh:mm a"/> <br/>
</p>

    
