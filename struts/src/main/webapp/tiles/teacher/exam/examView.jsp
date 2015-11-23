<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<h2><s:text name="exam.header.questionOfExam"/></h2>
<s:form>
  <s:actionerror/>

  <s:iterator value="question">
    <h3><s:property value="id"/> - <s:property value="text"/></h3>

    <h4>Fragen:</h4>
    <s:iterator value="question">

      <s:if test="questionType.toString() == 'Cloze'">
        <p>Cloze: <s:property value="exercise"/></p>
        <p><s:property value="text"/> </p>
        <s:iterator value="exercise.blankSet">
        <p>
            <s:iterator value="blankSolutions"/>
              <s:property value="text"/>
        </s:iterator>
        </p>
      </s:if>

      <s:elseif test="questionType.toString() == 'MultipleSelection'">
        <p>Multiple-Choice: <s:property value="exercise"/>
          <br><s:property value="text"/></p>

        <!-- <s:checkboxlist name="yourChoice" label="Multiple Choice" list="exercise.multipleChoiceEntrySet" /> -->

        <ul>
          <s:iterator value="exercise.multipleChoiceEntrySet">
           <li><s:property value="text"/> (<s:property value="isSolution"/>)</li>
          </s:iterator>
        </ul>
      </s:elseif>

      <s:elseif test="questionType.toString() == 'SingleSelection'">
        <!-- Single Choice darstellen -->
        <p>Single Choice: <s:property value="exercise"/>
            <br><s:property value="text"/>
        </p>
        <ul>
          <s:iterator value="exercise.multipleChoiceEntrySet">
            <li> <s:property value="text"/> (<s:property value="isSolution"/>) </li>
          </s:iterator>
        </ul>
      </s:elseif>
      <s:else>
        <p>ExerciseType not recognized</p>
      </s:else>

      <hr>
    </s:iterator>

  </s:iterator>
</s:form>