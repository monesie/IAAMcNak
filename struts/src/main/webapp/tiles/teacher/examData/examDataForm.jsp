<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2><s:text name="examData.header.editExam"/></h2>
<s:actionerror/>
<s:form theme="xhtml">
  <s:hidden name="examDataId"/>
  <s:hidden name="examData.id"/>
  <s:hidden name="examData.status"/>
  <s:textfield key="examData.examName" requiredLabel="true"/>
  <s:textfield key="examData.courseName" requiredLabel="true"/>
  <sx:datetimepicker key="examData.lastCourseDay" requiredLabel="true" displayFormat="dd.MM.yyyy"/>
  <sx:datetimepicker key="examData.startDate" requiredLabel="true" displayFormat="dd.MM.yyyy"/>
  <sx:datetimepicker key="examData.endDate" requiredLabel="true" displayFormat="dd.MM.yyyy"/>
  <s:textfield key="examData.duration" requiredLabel="true"/>
  <s:select key="examData.creditPoint" list="{'0,5','0,75','1'}" requiredLabel="true"/>
  <s:textfield key="examData.passLimit" requiredLabel="true"/>
  <s:select key="examData.evaluationRule" list="{'FullPoints','PartialPoint'}" requiredLabel="true"/>
  <s:checkbox key="examData.minusPoints" requiredLabel="true"/>

  <s:submit key="button.save" action="saveExamData"/>
  <s:submit key="button.cancel" action="goBackToExamDataListTable"/>
</s:form>
