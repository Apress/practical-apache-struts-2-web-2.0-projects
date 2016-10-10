<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="home.title" /></title>
</head>
<body>

<p><s:text name="home.welcomeText"/></p>

<div>
    <p><s:text name="home.mostRecentTen"/></p>
    <s:action name="showRecentEvents" namespace="/search" executeResult="true" >
        <s:param name="number">10</s:param>
    </s:action>
</div>

</body>
</html>