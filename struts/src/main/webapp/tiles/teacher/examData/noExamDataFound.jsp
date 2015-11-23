<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2><s:text name="examData.header.listOfExamData"/></h2>

<h3><s:text name="examData.noEntry"/></h3>
<s:form>
<s:submit key="button.createExam" action="createExamData" theme="simple"/>
</s:form>
