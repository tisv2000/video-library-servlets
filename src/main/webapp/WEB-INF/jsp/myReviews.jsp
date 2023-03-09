<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2 style="color: darkgreen;margin-left:20px"><fmt:message key="myReviews.title"/></h2>
<table style="width: 100%;margin-left:20px">
    <tr style="text-align: left">
        <th><fmt:message key="myReviews.movieTitle"/></th>
        <th><fmt:message key="myReviews.rate"/></th>
        <th><fmt:message key="myReviews.review"/></th>
    </tr>

    <c:forEach var="review" items="${requestScope.reviews}">
        <tr>
            <td>${review.movie.title}</td>
            <td>${review.rate}</td>
            <td>${review.text}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
