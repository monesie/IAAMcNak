<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:form>

    <s:if test="user.userName != null && user.userName.isEmpty() == false">
        <s:textfield key="user.userName" requiredLabel="true" readonly="true"/>
    </s:if>
    <s:else>
        <s:textfield key="user.userName" requiredLabel="true"/>
    </s:else>
    <s:textfield key="user.firstName" requiredLabel="true"/>
    <s:textfield key="user.lastName" requiredLabel="true"/>
    <s:textfield key="user.email" requiredLabel="true"/>
    <s:textfield key="user.century"/>
    <s:select key="select.userRoles"
              headerKey="1"
              list="roleNames"
              name="user.role"/>
  <s:submit key="button.save" action="saveUser"/>
  <s:submit key="button.cancel" action="cancelUser"/>
</s:form>
