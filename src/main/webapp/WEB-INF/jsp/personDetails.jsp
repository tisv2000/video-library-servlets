<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2 style="margin-left: 20px"><fmt:message key="personDetails.title"/></h2>
<div style="margin-left:20px">
    <span><b><fmt:message key="personDetails.Name"/>:</b> ${requestScope.person.name}</span> <br>
    <span><b><fmt:message key="personDetails.Birthday"/>:</b> ${requestScope.person.birthday}</span> <br>
</div>
</body>
</html>
