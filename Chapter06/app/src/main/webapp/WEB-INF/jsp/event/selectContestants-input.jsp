<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="createEvent.selectContestants.title" /></title>
</head>
<body>

<s:actionerror />

<s:form action="removeContestants" namespace="/event" method="post" >

    <s:checkboxlist name="selectedContestants" theme="apress"
            list="options" listKey="name" listValue="name+' - '+description" />
    <s:submit key="button.remove" />

</s:form>

<s:url id="saveEvent" action="saveEvent" namespace="/event" />
<s:a href="%{saveEvent}"><s:text name="link.completeEvent" /></s:a>
|
<s:url id="enterContentant" action="enterContestantDetails" method="input" namespace="/event" />
<s:a href="%{enterContentant}"><s:text name="link.addContestant" /></s:a>

</body>
</html>