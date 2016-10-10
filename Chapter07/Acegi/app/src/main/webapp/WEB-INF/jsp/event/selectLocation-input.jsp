<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="createEvent.selectLocation.title" /></title>
</head>
<body>

<s:form action="selectLocation" namespace="/event" method="post" >

    <s:select size="10" name="selectedLocation"
            list="locations" listKey="id" listValue="name" />
    <s:submit key="button.select" />

</s:form>

<s:url id="addLocation" action="selectLocationType" namespace="/event" method="input" />
<s:a href="%{addLocation}"><s:text name="link.addLocation" /></s:a>

</body>
</html>