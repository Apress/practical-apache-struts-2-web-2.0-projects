<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="createEvent.enterLocationDetails.address.title" /></title>
</head>
<body>

<s:form action="enterLocationDetails" namespace="/event" method="post" >

    <s:textfield key="address.name" name="address.name" value="%{address.name}"/>
    <s:textfield key="address.address" name="address.address" />
    <s:textfield key="address.city" name="address.city" />
    <s:textfield key="address.state" name="address.state" />
    <s:textfield key="address.zipcode" name="address.zipcode" />

    <s:hidden name="typeClass"/>

    <s:submit key="button.create" />

</s:form>

</body>
</html>