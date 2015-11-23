<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:text name="user.header.listOfUser"/>
<s:form>
  <s:actionerror/>
  <table>
    <tr>
      <th></th>
        <th><s:text name="user.userName"/></th>
      <th><s:text name="user.firstName"/></th>
        <th><s:text name="user.lastName"/></th>
        <th><s:text name="user.email"/></th>
        <th><s:text name="user.century"/></th>
      <th><s:text name="user.role"/></th>
    </tr>
    <s:iterator value="userList">
      <tr>
              <td><s:radio name="userName" list="#{userName:''}" theme="simple"/></td>
              <td><s:property value="userName"/> </td>
              <td><s:property value="firstName"/></td>
              <td><s:property value="lastName"/></td>
              <td><s:property value="email"/></td>
              <td><s:property value="century"/></td>
              <td><s:property value="role"/></td>
      </tr>

    </s:iterator>
  </table>

  <s:submit key="button.createUser" action="newUser"/>
  <s:submit key="button.edit" action="editUser"/>
  <s:submit key="button.resetPassword" action="resetPassword"/>
  <s:submit key="button.deleteUser" action="deleteUser"/>

</s:form>
