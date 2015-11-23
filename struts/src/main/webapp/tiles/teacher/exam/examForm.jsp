<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2><s:text name="exam.header.form"/></h2>
<s:actionerror/>
<s:form theme="xhtml">
  <s:hidden name="examDataId"/>
  <s:hidden name="exam.id"/>
  <sx:autocompleter label="exam.participantList" name="participantToAdd"
                    autoComplete="true" list="possibleParticipantList"
                    forceValidOption="true"/>
  <s:submit key="button.add" action="createExam"/>
  <s:submit key="button.back" action="goBackToExamDataListTable"/>
  <table>
    <tr>
      <th><s:text name="user.userName"/></th>
      <th><s:text name="user.firstName"/></th>
      <th><s:text name="user.lastName"/></th>
      <th><s:text name="user.century"/></th>
    </tr>
    <s:iterator value="participantAddedList">
      <tr>
        <td><s:property value="userName"/></td>
        <td><s:property value="firstName"/></td>
        <td><s:property value="lastName"/></td>
        <td><s:property value="century"/></td>
      </tr>
    </s:iterator>
  </table>

</s:form>

