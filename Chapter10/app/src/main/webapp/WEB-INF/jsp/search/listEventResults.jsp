<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="searchResults.title" /></title>
</head>
<body>

<h4><s:text name="searchResults.query"/> <s:property value="userInfo" /></h4>

<div>
<s:if test="results.size()==0">
    <s:text name="text.noEvents" />
</s:if>
<s:else>
    <s:iterator id="next" value="results">
        <s:component template="eventListing.jsp" theme="%{eventTemplate}">
            <s:param name="event" value="#next" />
        </s:component>
    </s:iterator>
</s:else>
</div>

</body>
</html>