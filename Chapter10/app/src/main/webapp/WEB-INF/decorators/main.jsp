<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title><decorator:title default="Struts Starter"/></title>
    <link href="<s:url value='/styles/main.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <s:head theme="ajax" />
    <decorator:head/>
</head>
<body id="page-home"
      onload="<decorator:getProperty property="body.onload"/>"
      onunload="<decorator:getProperty property="body.unonload"/>">

<div id="page">
    <div id="header" class="clearfix">
        <s:text name="header.text" />
    </div>

    <div align="right" style="position:relative;top:-40px;">
        <s:form namespace="/search" action="searchByTitle" method="POST" theme="simple">
            <s:text name="text.search" />
            <s:textfield name="titlePartial" size="15"/>
            <s:submit key="button.search" />
            <%--<s:submit key="button.search" theme="ajax" targets="main"/>--%>
        </s:form>
    </div>

    <div id="content" class="clearfix">
        <div id="main">
            <decorator:body/>
        </div>

        <div id="local">
            <h3><s:text name="leftnav.title"/></h3>
            <ul>
                <s:if test="#session['user']==null">
                    <s:url id="register" action="findUser" namespace="/user" />
                    <li><s:a href="%{register}"><s:text name="link.register" /></s:a></li>
                    <s:url id="logon" action="authenticate" namespace="/" />
                    <li><s:a href="%{logon}"><s:text name="link.logon" /></s:a></li>
                </s:if>
                <s:else>
                    <s:url id="update" action="findUser" namespace="/user" >
                        <s:param name="emailId" value="#session['user'].email" />
                    </s:url>
                    <li><s:a href="%{update}"><s:text name="link.updateProfile" /></s:a></li>

                    <s:url id="logoff" action="logoff" namespace="/" />
                    <li><s:a href="%{logoff}"><s:text name="link.logoff" /></s:a></li>
                </s:else>

                <s:url id="newEvent" action="addEventFlow" namespace="/event" />
                <li><s:a href="%{newEvent}"><s:text name="link.addEvent" /></s:a></li>

                <s:url id="recentEvents" action="showRecentEvents" namespace="/search" />
                <li><s:a href="%{recentEvents}"><s:text name="link.recentEvents" /></s:a></li>

                <s:url id="search" action="searchEvents" namespace="/search" />
                <li><s:a href="%{search}"><s:text name="link.search" /></s:a></li>
            </ul>
        </div>

    </div>

    <div id="footer" class="clearfix">
        <s:text name="footer.text" />
    </div>

</div>

</body>
</html>
