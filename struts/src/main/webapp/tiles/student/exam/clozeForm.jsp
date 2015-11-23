<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2><s:text name="exam.header.questionOfExam"/></h2>
<s:form theme="xhtml">
    <s:property value="question.text"/>

    <s:iterator value="answerList" status="status" var="answer">
        <s:property value="answer.blank"/>
        <s:textfield label="%{#status.count}" name="blankList[%{#status.count}]"/>
    </s:iterator>

    <s:submit key="button.nextQuestion" action="saveClozeUserEntry"/>
</s:form>