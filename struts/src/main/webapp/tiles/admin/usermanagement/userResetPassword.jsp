<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:form>
  <s:textfield key="user.userName" name="userName" requiredLabel="true" readonly="true"/>
  <s:password key="login.lbl.password" name="newPassword" requiredLabel="true"/>


  <s:submit key="button.save" action="savePassword"/>
</s:form>

