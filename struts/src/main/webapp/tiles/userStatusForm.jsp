<%@ page import="java.util.Date" %>
<%@ taglib prefix="ww" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>

<ww:if test="#session.loggedIn == 'true'">
<div>Welcome, you have logged in.<br/>
  User Name: <ww:property  value="#session.username"/> <br/>
  Your Role: <ww:property value="#session.role"/> <br/>
    Logged In since: <%=new Date(session.getCreationTime())%>
</div>
  <input  type = "submit"
          value = "Logout"
          onclick = "location.pathname='logout.action';"
          />
</ww:if>
