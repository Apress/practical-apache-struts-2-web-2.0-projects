<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<p>
<h3><s:property value="parameters.event.name" /></h3>
<ul>
    <li>
        <s:text name="display.event.date"/>
        <s:date name="parameters.event.startTime" format="MMM d, yyyy"/>
    </li>
    <li><s:text name="display.location" />
        <s:property value="parameters.event.location.name" /> :
        <s:property value="parameters.event.location.city" />,
        <s:property value="parameters.event.location.state" />
    </li>
    <li><s:text name="display.contestants" />
        <s:iterator status="rowstatus" value="parameters.event.options">
            <s:property value="name"/>
            <s:if test="#rowstatus.last==false" >,</s:if>
        </s:iterator>
    </li>
</ul>
</p>

    
