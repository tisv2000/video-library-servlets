<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h3 style="color: darkgreen;margin-left: 20px"><fmt:message key="allReviews.title"/></h3>
<form action="/reviews" method="get" style="margin-left:20px">
    <label for="emailId"><fmt:message key="allReviews.email"/>:
        <input type="text" name="email" value="${param.email}" id="emailId">
    </label>
    <button type="submit" style="margin-left: 10px"><fmt:message key="allReviews.findButton"/></button>
    <br>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>

<h3 style="color: darkgreen;margin-left:20px"><fmt:message key="allReviews.resultList"/></h3>
<table style="width: 100%;margin-left:20px">
    <tr style="text-align: left">
        <th><fmt:message key="allReviews.movie"/></th>
        <th><fmt:message key="allReviews.rate"/></th>
        <th><fmt:message key="allReviews.review"/></th>
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
