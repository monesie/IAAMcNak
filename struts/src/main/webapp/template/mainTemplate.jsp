<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <meta charset="UTF-8">
    <title><tiles:insertAttribute name="title"/></title>
    <s:head/>
    <sx:head/>
</head>
<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="userStatus"/>
<hr/>
<tiles:insertAttribute name="content"/>
<hr/>
<tiles:insertAttribute name="footer"/>
</body>
</html>