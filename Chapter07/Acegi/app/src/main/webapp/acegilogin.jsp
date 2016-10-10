<%@ page import="org.acegisecurity.AuthenticationException" %>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="home.logon" /></title>
</head>
<body>

<s:if test="#parameters.size()>0">
    <s:text name="text.error.reason" />:  
    <s:property value="#session['ACEGI_SECURITY_LAST_EXCEPTION'].message" /><br/>
    <s:text name="text.register"/><br/><br/>
</s:if>

<form action="<%=request.getContextPath()%>/j_acegi_security_check" method="POST" >
    <s:text name="logon.username"/> <input type="text" name="j_username" /> <br/>
    <s:text name="logon.password"/> <input type="password" name="j_password" /> <br/>
    <input type="submit" value="<s:text name="button.logon" />" />
</form>

</body>
</html>
