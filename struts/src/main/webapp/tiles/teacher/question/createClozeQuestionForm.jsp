<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2><s:text name="question.header.editQuestion"/></h2>
<s:form theme="xhtml">
  <s:actionerror/>
  <div><s:property value="examDataId"/></div>

  <s:hidden name="question.id"/>
  <s:label key="question.questionType" value="'Cloze'"/>
  <s:text name="cloze.exampleText"/>
  <s:textarea key="question.text" name="question.text" requiredLabel="true"/>
  <s:textfield key="question.points" name="question.points" requiredLabel="true"/>

  <s:submit key="button.generateBlanks" action="saveClozeQuestion"/>
  <s:submit key="button.cancel" action="cancelQuestionForm"/>
</s:form>