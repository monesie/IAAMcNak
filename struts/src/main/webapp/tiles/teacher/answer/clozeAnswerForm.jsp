<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:form>
  <s:hidden name="questionId"/>
  <s:property value="answer.question.text"/>

  <s:hidden name="answer.id"/>
  <s:hidden name="answer.blank"/>
  <s:textfield name="answer.text" key="answer.text" requiredLabel="true"/>
  <s:hidden name="answer.solution"/>

  <s:submit key="button.save" action="saveClozeAnswer"/>
</s:form>