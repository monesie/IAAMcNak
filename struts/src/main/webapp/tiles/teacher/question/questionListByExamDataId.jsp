<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2><s:text name="question.header.questionsOfExamData"/></h2>
<s:hidden value="examDataId"/>
<s:form>

    <s:actionerror/>
    <h3><s:text name="question.examData"/></h3>
    <table>
        <tr>
            <td>
            <s:radio name="questionId" list="questionList" listKey="id"
                     theme="mcNakTheme" value="defaultQuestionId" cssClass="radioMap"/>
            </td>
            <td>
                <s:submit key="button.addMultipleChoiceQuestion" action="addMultipleChoiceQuestion" theme="simple"/><br>
                <s:submit key="button.addClozeQuestion" action="addClozeQuestion" theme="simple"/><br>
                <s:submit key="button.edit" action="editQuestion" theme="simple"/><br>
                <s:submit key="button.delete" action="deleteQuestion" theme="simple"/><br>
                <s:submit key="button.answerManagement" action="putCurrentQuestionIdIntoSession" theme="simple"/><br>
                <s:submit key="button.cancel" action="cancelQuestionListForm" theme="simple"/><br>
            </td>
        </tr>
    </table>

</s:form>

