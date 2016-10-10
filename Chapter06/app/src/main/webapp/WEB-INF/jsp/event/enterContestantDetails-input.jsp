<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="createEvent.enterContestantDetails.title" /></title>
</head>
<body>

<s:form action="enterContestantDetails" namespace="/event" method="post" >

    <s:textfield key="contestant.name" name="contestant.name" />
    <s:textfield key="contestant.description" name="contestant.description" />

    <s:submit key="button.create" />

</s:form>

</body>
</html>