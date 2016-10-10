<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="api.event.title" /></title>
</head>
<body>

<table>
    <tr><td colspan="2"><h1><s:text name="event.title" /></h1></td></tr>
    <tr>
        <td><s:text name="event.name" /></td>
        <td><s:property value="name"/></td>
    </tr>
    <tr>
        <td><s:text name="event.startTime" /></td>
        <td><s:date name="startTime" format="MM/dd/yyyy hh:mm"/></td>
    </tr>
    <tr>
        <td><s:text name="event.votingStartTime" /></td>
        <td><s:date name="votingStartTime" format="MM/dd/yyyy hh:mm"/></td>
    </tr>
    <tr>
        <td><s:text name="event.duration" /></td>
        <td><s:property value="duration"/></td>
    </tr>
    <tr>
        <td><s:text name="event.timeZoneOffset" /></td>
        <td><s:property value="timeZoneOffset"/></td>
    </tr>
    <tr>
        <td><s:text name="event.progress" /></td>
        <td><s:property value="status"/></td>
    </tr>

    <tr>
        <td colspan="2"><br/><h3><s:text name="address.title" /></h3></td>
    </tr>
    <s:if test="location.class.name.endsWith('.Address')">
        <tr>
            <td><s:text name="address.name"/></td>
            <td><s:property value="location.name" /></td>
        </tr>
        <tr>
            <td><s:text name="address.address"/></td>
            <td><s:property value="location.address" /></td>
        </tr>
        <tr>
            <td><s:text name="address.city"/></td>
            <td><s:property value="location.city" /></td>
        </tr>
        <tr>
            <td><s:text name="address.state"/></td>
            <td><s:property value="location.state" /></td>
        </tr>
        <tr>
            <td><s:text name="address.zipcode"/></td>
            <td><s:property value="location.zipcode" /></td>
        </tr>
    </s:if>
    <s:else>
        <tr>
            <td><s:text name="broadcast.name"/></td>
            <td><s:property value="location.name" /></td>
        </tr>
        <tr>
            <td><s:text name="broadcast.city"/></td>
            <td><s:property value="location.city" /></td>
        </tr>
        <tr>
            <td><s:text name="broadcast.state"/></td>
            <td><s:property value="location.state" /></td>
        </tr>
        <tr>
            <td><s:text name="broadcast.network"/></td>
            <td><s:property value="location.network" /></td>
        </tr>
        <tr>
            <td><s:text name="broadcast.stationIdentifier"/></td>
            <td><s:property value="location.stationIdentifier" /></td>
        </tr>
    </s:else>

    <tr>
        <td colspan="2"><br/><h3><s:text name="contestant.title" /></h3></td>
    </tr>
    <s:iterator value="options" >
        <tr>
            <td colspan="2"><s:property value="name" /> - <s:property value="description" /> </td>
        </tr>
    </s:iterator>

</table>

</body>
</html>