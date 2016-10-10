<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="home.title" /></title>
</head>
<body>

<authz:authorize ifNotGranted="ROLE_USER">

    <s:url id="register" action="findUser" namespace="/user" />
    <s:a href="%{register}"><s:text name="link.register" /></s:a>

    <s:url id="login" action="acegilogin" namespace="/" />
    |  <s:a href="%{login}"><s:text name="home.logon" /></s:a>
</authz:authorize>
<authz:authorize ifAllGranted="ROLE_USER">
    <s:url id="update" action="findUser" namespace="/user" >
        <s:param name="emailId" value="authenticatedUser.username" />
    </s:url>
    <s:a href="%{update}"><s:text name="link.updateProfile" /></s:a>

    |  <a href="<%=request.getContextPath()%>/j_acegi_logout"><s:text name="link.logoff" /></a>
</authz:authorize>

<s:url id="newEvent" action="addEventFlow" namespace="/event" />
|  <s:a href="%{newEvent}"><s:text name="link.addEvent" /></s:a>

</body>
</html>