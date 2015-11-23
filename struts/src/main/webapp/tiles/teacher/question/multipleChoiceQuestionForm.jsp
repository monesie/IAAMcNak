<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2><s:text name="question.header.editQuestion"/></h2>
<s:actionerror/>
<s:form theme="xhtml">
  <div><s:property value="examDataId"/></div>

  <s:hidden name="question.id"/>
  <s:select key="question.questionType" list="{'SingleSelection','MultipleSelection'}" requiredLabel="true"/>

  <s:textarea key="question.text" name="question.text" requiredLabel="true"/>
  <s:textfield key="question.points" name="question.points" requiredLabel="true"/>

  <s:submit key="button.save" action="saveQuestion"/>
  <s:submit key="button.cancel" action="cancelQuestionForm"/>
</s:form>