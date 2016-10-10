<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="createEvent.selectLocationType.title" /></title>
</head>
<body>

<s:form action="enterLocationDetails" namespace="/event" method="post" >
                
    <s:radio theme="apress" list="types" name="typeClass"
            listKey="class.name" listValue="getText(class.name)" />
    <s:hidden name="setup" value="true" />

    <s:submit key="button.select" />

</s:form>

</body>
</html>