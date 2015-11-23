<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2><s:text name="answer.tableHeader"/></h2>

<h3><s:text name="answer.noEntry"/></h3>
<s:form>
<s:submit key="button.add" action="addMultipleChoiceAnswer" theme="simple"/>
</s:form>