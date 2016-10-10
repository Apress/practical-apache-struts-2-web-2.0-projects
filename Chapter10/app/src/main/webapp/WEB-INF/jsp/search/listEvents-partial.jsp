<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:if test="results.size()==0">
    <s:text name="text.noEvents" />
</s:if>
<s:else>
    <s:iterator id="next" value="results">
        <s:component template="eventListing.jsp" theme="apress">
            <s:param name="event" value="#next" />
        </s:component>
    </s:iterator>
</s:else>