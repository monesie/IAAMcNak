<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2><s:text name="teacher.exam.examListTable"/></h2>
<s:form>
  <s:actionerror/>
  <s:hidden name="examDataId"/>
  <s:property value="examData.examName"/>
  <s:property value="examData.courseName"/>
  <table>
    <tr>
      <th></th>
      <th><s:text name="user.userName"/></th>
      <th><s:text name="user.firstName"/></th>
      <th><s:text name="user.lastName"/></th>
      <th><s:text name="exam.oneTimePassword"/></th>
    </tr>
    <s:iterator value="examListByExamData">
      <tr>
        <td><s:radio name="examId" list="#{id:''}" theme="simple"/></td>
        <td><s:property value="participant.userName"/></td>
        <td><s:property value="participant.firstName"/></td>
        <td><s:property value="participant.lastName"/></td>
        <td><s:property value="oneTimePassword"/></td>
      </tr>
    </s:iterator>
  </table>

  <s:submit key="button.delete" action="deleteExam"/>
  <s:submit key="button.back" action="goBackToExamDataListTable"/>
</s:form>
