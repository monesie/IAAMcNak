<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div><s:text name="blank.headerText"/></div>
<div><s:text name="blank.exampleText"/></div>
<s:actionerror/>
<s:form>
<s:hidden name="questionId"/>
<s:property value="answer.question.text"/>

<s:iterator value="blankList" status="status" var="answer">
  <s:property value="answer.blank"/>
  <s:textfield label="%{#status.count}" name="blankList[%{#status.count}].text" requiredLabel="true"/>
</s:iterator>

  <s:submit key="button.save" action="saveClozeAnswerList"/>
  <s:submit key="button.back" action="goBackToAnswerManagement"/>
</s:form>