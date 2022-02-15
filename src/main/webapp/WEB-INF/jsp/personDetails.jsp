<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2 style="margin-left: 20px">Person details</h2>
<%--<img src="/images${requestScope.person.image}" width="auto" height="50%" style="float:left;margin-left: 20px;margin-right: 10px"/>--%>
<div style="align-content:center;margin-right: 50px">
    <span><b>Name:</b> ${requestScope.person.name}</span> <br>
    <span><b>Birthday:</b> ${requestScope.person.birthday}</span> <br>
</div>
</body>
</html>
