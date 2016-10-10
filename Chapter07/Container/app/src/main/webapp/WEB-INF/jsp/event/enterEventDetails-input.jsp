<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="createEvent.enterEvent.title" /></title>
</head>
<body>

Authentication Information:<br/>
<s:property value="principal.remoteUser" /> <br/>
<s:property value="principal.isUserInRole('user')" /> <br/>
<s:property value="principal.isUserInRole('registered')" /> <br/>

<s:form action="enterEventDetails" namespace="/event" method="post" >

    <s:textfield key="event.name" name="name" />
    <s:textfield key="event.startDate" name="partialStartDate" />
    <s:textfield key="event.startTime" name="patialStartTime" />
    <s:textfield key="event.timeZoneOffset" name="timeZoneOffset" />
    <s:textfield key="event.votingStartTime" name="partialVotingStartTime" />
    <s:textfield key="event.duration" name="duration" />

    <s:submit key="button.create" />

</s:form>

</body>
</html>