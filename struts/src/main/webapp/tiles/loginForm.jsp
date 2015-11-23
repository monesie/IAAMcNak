<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form>
  <s:label name="serverResponse"/>
  <s:textfield key="login.lbl.username" name="username" requiredLabel="true" />
  <s:password key="login.lbl.password" name="password" requiredLabel="true"/>
  <s:submit key="login.btn.logIn" action="validate"/>
</s:form>

