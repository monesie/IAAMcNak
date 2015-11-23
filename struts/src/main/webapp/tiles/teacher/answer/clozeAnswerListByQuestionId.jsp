<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2><s:text name="answer.tableHeader"/></h2>
<s:hidden value="questionId"/>
<s:form>
  <s:actionerror/>
  <table>
    <tr>
      <td>
        <s:property value="answer.question.text"/>
        <s:radio name="answerId" list="answerList" listKey="id"
                 theme="mcNakTheme" value="defaultAnswerId" cssClass="radioMap"/>
      </td>
      <td>
        <s:submit key="button.edit" action="editAnswer" theme="simple"/><br>
        <s:submit key="button.cancel" action="loadAllQuestions" theme="simple"/><br>
      </td>
    </tr>
  </table>
</s:form>

