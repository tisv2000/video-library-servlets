<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title 1</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>List of all movies</h1>
<table>
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>Country</th>
        <th>Genre</th>
    </tr>

    <c:forEach var="movie" items="${requestScope.movies}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/movies/${movie.id}">${movie.title}</a>
            </td>
            <td>${movie.year}</td>
            <td>${movie.country}</td>
            <td>${fn:toLowerCase(movie.genre)}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
