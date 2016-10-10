<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="createEvent.enterLocationDetails.broadcast.title" /></title>
</head>
<body>

<s:form action="enterLocationDetails" namespace="/event" method="post" >

    <s:textfield key="broadcast.name" name="broadcast.name" />
    <s:textfield key="broadcast.city" name="broadcast.city" />
    <s:textfield key="broadcast.state" name="broadcast.state" />
    <s:textfield key="broadcast.network" name="broadcast.network" />
    <s:textfield key="broadcast.stationIdentifier" name="broadcast.stationIdentifier" />

    <s:hidden name="typeClass"/>
    
    <s:submit key="button.create" />

</s:form>

</body>
</html>