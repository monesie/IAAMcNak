<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<s:form>
  <s:actionerror/>
  <table>
    <tr>
      <td>
        <h2><s:text name="exam.header.listOfOpenExams"/></h2>
        <s:radio name="examId" list="openExamList" listKey="id"
                 theme="mcNakTheme" value="defaultExamId" cssClass="radioMap"/>
      </td>
      <td>
        <s:submit key="button.takeExam" action="takeExam" theme="simple"/>
      </td>
    </tr>
    <tr>
      <td>
      <h2><s:text name="exam.header.listOfSolvedExams"/></h2>
      <table>
        <tr>
          <th><s:text name="exam.courseName"/></th>
          <th><s:text name="exam.finalDate"/></th>
          <th><s:text name="exam.passed"/></th>
        </tr>
        <s:iterator value="solvedExamList">
          <tr>
            <td><s:property value="examData.courseName"/></td>
            <td><s:property value="startDate"/></td>
            <td><s:if test="true.equals(passed)">
              <s:text name="exam.passedTrue"/>
            </s:if>
              <s:else>
                <s:text name="exam.passedFalse"/>
              </s:else></td>
          </tr>

        </s:iterator>
      </table>
      </td>
    </tr>
  </table>
</s:form>


