<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<head>--%>
<%--    <title>Movie details</title>--%>
<%--</head>--%>
<body>
<%@include file="header.jsp" %>

<h1>Movie details</h1>
<div>
    Title: ${requestScope.movie.title} <br>
    Release year: ${requestScope.movie.year} <br>
    Country: ${requestScope.movie.country} <br>
</div>
</body>
</html>
