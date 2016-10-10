<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="home.title" /></title>
</head>
<body>

<s:if test="principal.remoteUser==null">

    <s:url id="register" action="findUser" namespace="/user" />
    <s:a href="%{register}"><s:text name="link.register" /></s:a>

    <s:url id="logon" action="logon" namespace="/" />
    |  <s:a href="%{logon}"><s:text name="link.logon" /></s:a>

</s:if>
<s:else>
    <s:text name="text.remoteUser" /> <s:property value="principal.remoteUser" /> <br/><br/>

    <s:url id="update" action="findUser" namespace="/user" >
        <s:param name="emailId" value="principal.remoteUser" />
    </s:url>
    <s:a href="%{update}"><s:text name="link.updateProfile" /></s:a>
</s:else>

<s:if test="principal.isUserInRole('registered')">
    <s:url id="newEvent" action="addEventFlow" namespace="/event" />
    |  <s:a href="%{newEvent}"><s:text name="link.addEvent" /></s:a>
</s:if>

</body>
</html>