<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2><s:text name="exam.header.questionOfExam"/></h2>
<s:actionerror/>
<s:actionmessage/>
<s:form theme="xhtml">
  <s:property value="question.text" />
  <s:checkboxlist  list="answerList" name="resultList" listKey="id" listValue="text"/>


  <s:submit key="button.nextQuestion" action="saveMCUserEntry"/>
</s:form>