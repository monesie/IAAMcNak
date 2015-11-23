<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2><s:text name="examData.header.listOfExamData"/></h2>
<s:form>
    <s:actionerror/>
    <table>
        <tr>
            <td>
    <s:radio name="examDataId" list="examDataList" listKey="id"
             theme="mcNakTheme" value="defaultExamDataId" cssClass="radioMap"/>
    
            </td>
            <td>
                <s:submit key="button.createExam" action="createExamData" theme="simple"/><br>
                <s:submit key="button.editExamData" action="editExamData" theme="simple"/><br>
                <s:submit key="button.delete" action="deleteExamData" theme="simple"/><br>
                <s:submit key="button.questionManagement" action="loadAllQuestions" theme="simple"/><br>
                <s:submit key="button.participantManagement" action="addParticipant" theme="simple"/><br>
                <s:submit key="button.showParticipants" action="showExamList" theme="simple"/><br>
                <s:submit key="button.examDataReady" action="examDataReady" theme="simple"/><br>
            </td>
        </tr>
    </table>

</s:form>


