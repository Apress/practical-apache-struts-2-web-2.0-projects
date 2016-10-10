<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<?xml version="1.0" encoding="UTF-8"?>

<event id="<s:property value="id"/>">

    <name><s:property value="name"/></name>
    <startTime><s:date name="startTime" format="MM/dd/yyyy hh:mm"/></startTime>
    <votingStartTime><s:date name="votingStartTime" format="MM/dd/yyyy hh:mm"/></votingStartTime>
    <duration><s:property value="duration"/></duration>
    <timeZoneOffset><s:property value="timeZoneOffset"/></timeZoneOffset>
    <status><s:property value="status"/></status>

    <s:if test="location.class.name.endsWith('.Address')">
    <address type="Adress">
        <name><s:property value="location.name" /></name>
        <address><s:property value="location.address" /></address>
        <city><s:property value="location.city" /></city>
        <state><s:property value="location.state" /></state>
        <zipcode><s:property value="location.zipcode" /></zipcode>
    </s:if>
    <s:else>
    <address type="Broadcast">
        <name><s:property value="location.name" /></name>
        <city><s:property value="location.city" /></city>
        <state><s:property value="location.state" /></state>
        <network><s:property value="location.network" /></network>
        <stationIdentifier><s:property value="location.stationIdentifier" /></stationIdentifier>
    </s:else>
    </address>

    <contestants>
    <s:iterator value="options" >
        <contestant>
            <name><s:property value="name" /></name>
            <description><s:property value="description" /></description>
        </contestant>
    </s:iterator>
    </contestants>

</event>