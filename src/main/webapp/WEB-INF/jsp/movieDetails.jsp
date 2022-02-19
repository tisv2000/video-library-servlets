<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<head>--%>
<%--    <title>Movie details</title>--%>
<%--</head>--%>
<body>
<%@include file="header.jsp" %>
<h2 style="margin-left: 20px">Movie details</h2>
<img src="/images/movies/${requestScope.movie.image}.jpeg" width="auto" height="50%"
     style="float:left;margin-left: 20px;margin-right: 10px"/>
<div style="align-content:center;margin-right: 50px">
    <span><b>Title:</b> ${requestScope.movie.title}</span> <br>
    <span><b>Release year:</b> ${requestScope.movie.year}</span> <br>
    <span><b>Country:</b> ${requestScope.movie.country}</span> <br><br>
    <span><b>Description:</b> ${requestScope.movie.description}</span> <br>
</div>

<h3 style="color: blueviolet">Add review</h3>
<form action="${pageContext.request.contextPath}/movies/${requestScope.movie.id}" method="post">
    <label for="textId">Text:
        <input type="text" name="text" id="textId">
    </label>
    <label for="rateId" style="margin-left: 10px">Rate (1-10):
        <input type="range" min="1" max="10" name="rate" id="rateId">
    </label>
    <input hidden name="movieId" value="${requestScope.movie.id}">
    <button type="submit" style="margin-left: 10px">Add review</button>
    <br>
</form>

<h3 style="color: blueviolet">All review</h3>
<div>
    <table style="width: 60%">
        <tr>
            <th>Rate</th>
            <th>Review</th>
            <th>User</th>
        </tr>

        <c:forEach var="review" items="${requestScope.reviews}">
            <tr>
                <td>${review.rate}</td>
                <td>${review.text}</td>
                <td>${review.user.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
