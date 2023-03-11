<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2 style="margin-left: 20px;color: red"><fmt:message key="personDetails.title"/></h2>
<div>
    <img src="/images/persons/${requestScope.person.image}.jpeg" width="auto" height="50%"
         style="float:left;margin-left: 20px;margin-right: 10px"/>
</div>
<div style="align-content:center;margin-right: 50px;margin-left: 20px">
    <span><b><fmt:message key="personDetails.Name"/>:</b> ${requestScope.person.name}</span> <br>
    <span><b><fmt:message key="personDetails.Birthday"/>:</b> ${requestScope.person.birthday}</span> <br>
</div>
<br>
<div style="margin-left:20px; float:left">
    <h3 style="color: red;float:left;margin-left:20px;margin-right:20px"><fmt:message key="movie.moviesListTitle"/></h3>
    <table style="width: 100%;margin-left:20px">
        <tr style="text-align: left">
            <th><fmt:message key="movie.title"/></th>
            <th><fmt:message key="movie.year"/></th>
            <th><fmt:message key="movie.country"/></th>
            <th><fmt:message key="movie.genre"/></th>
        </tr>

        <c:forEach var="movie" items="${requestScope.movies}">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/movies/${movie.id}">${movie.title}</a>
                </td>
                <td>${movie.year}</td>
                <td>${movie.country}</td>
                <td>${fn:toLowerCase(movie.genre.title)}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
