<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="user.findUser.title" /></title>
    <%--<s:head theme="ajax" />--%>
</head>
<body>

<s:if test="exception!=null" >
    <s:text name="info.emailAddressExists" />
</s:if>

<s:form namespace="/user" action="updateUser" method="post" enctype="multipart/form-data" >
    <!--theme="ajax" validate="true">-->

    <s:textfield key="user.firstname" name="firstName" />
    <s:textfield key="user.lastname" name="lastName" />
    <s:textfield key="user.email" name="email" />
    <s:password key="user.password" name="password" />

    <s:hidden name="emailId" />

    <s:file key="user.portrait" name="upload" />
    <tr>
        <td colspan="2">
            <s:url id="image" action="getPortrait" namespace="/user" >
                <s:param name="emailId" value="emailId" />
            </s:url>
            <img src="<s:property value="#image" />" alt="" /> <br/>
        </td>
    </tr>

    <s:if test="email==null">
        <s:submit key="button.register" />
    </s:if>
    <s:else>
        <s:submit key="button.update" />
    </s:else>

</s:form>

</body>
</html>