<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="home.logon" /></title>
</head>
<body>

<s:actionerror />

<s:form action="logon" namespace="/" method="POST" >
    <s:textfield key="logon.username" name="username"/>
    <s:password key="logon.password" name="password"/>
    <s:submit type="submit" key="button.logon" />
</s:form>

</body>
</html>