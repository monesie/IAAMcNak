<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2><s:text name="question.header.questionsOfExamData"/></h2>

<h3><s:text name="question.noEntry"/></h3>
<s:form>
<s:submit key="button.addMultipleChoiceQuestion" action="addMultipleChoiceQuestion" theme="simple"/><br>
<s:submit key="button.addClozeQuestion" action="addClozeQuestion" theme="simple"/>
</s:form>