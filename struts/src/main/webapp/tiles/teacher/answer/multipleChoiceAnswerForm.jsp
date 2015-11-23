<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>multipleChoiceAnswerForm</div>
<s:actionerror/>
<s:form>
  <s:hidden name="questionId"/>
  <s:property value="question.text"/>

  <s:hidden name="answer.id"/>
  <s:textfield name="answer.text" key="answer.text" requiredLabel="true"/>
  <s:checkbox name="answer.solution" key="answer.solution" requiredLabel="true"/>

  <s:submit key="button.save" action="saveMultipleChoiceAnswer"/>
  <s:submit key="button.back" action="goBackToAnswerManagement"/>
</s:form>