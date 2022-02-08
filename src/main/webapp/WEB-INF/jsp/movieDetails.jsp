<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<head>--%>
<%--    <title>Movie details</title>--%>
<%--</head>--%>
<body>
<%@include file="header.jsp" %>

<h1 style="margin-left: 20px">Movie details</h1>
<img src="/images${requestScope.movie.image}" width="auto" height="50%" style="float:left;margin-left: 20px;margin-right: 10px"/>
<div style="align-content:center;margin-right: 50px">
    <span><b>Title:</b> ${requestScope.movie.title}</span> <br>
    <span><b>Release year:</b> ${requestScope.movie.year}</span> <br>
    <span><b>Country:</b> ${requestScope.movie.country}</span> <br><br>
    <span><b>Description:</b> ${requestScope.movie.description}</span> <br>
</div>
</body>
</html>
