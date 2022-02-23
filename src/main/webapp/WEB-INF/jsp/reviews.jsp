<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h3 style="color: darkgreen;margin-left: 20px">Filter reviews</h3>
<form action="/reviews" method="get" style="margin-left:20px">
    <label for="emailId">User email:
        <input type="text" name="email" id="emailId">
    </label>
    <button type="submit" style="margin-left: 10px">Find</button>
    <br>
</form>

<h3 style="color: darkgreen;margin-left:20px">All reviews</h3>
<table style="width: 100%;margin-left:20px">
    <tr style="text-align: left">
        <th>Movie</th>
        <th>Rate</th>
        <th>Review</th>
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
