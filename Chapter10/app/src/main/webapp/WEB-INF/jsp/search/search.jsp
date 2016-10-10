<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="search.title" /></title>
</head>
<body>

<s:form namespace="/search" action="searchForEvents" method="POST" >

    <tr>
        <td colspan="2">
            <h4><s:text name="search.location.criteria" /></h4>
        </td>
    </tr>
    <s:textfield key="search.location.name" name="locationName"/>
    <s:textfield key="search.location.city" name="locationCity" />
    <s:textfield key="search.location.state" name="locationState" />

    <tr>
        <td colspan="2">
            <h4><s:text name="search.contestant.criteria" /></h4>
        </td>
    </tr>
    <s:textarea key="search.contestant" name="contestants" />

    <s:submit key="button.advSearch" />

</s:form>

</body>
</html>