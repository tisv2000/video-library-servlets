<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<head>--%>
<%--    <title>Movie details</title>--%>
<%--</head>--%>
<body>
<%@include file="header.jsp" %>
<h2 style="margin-left: 20px;color: blueviolet">Movie details</h2>
<img src="/images/movies/${requestScope.movie.image}.jpeg" width="auto" height="50%"
     style="float:left;margin-left: 20px;margin-right: 10px"/>
<div style="align-content:center;margin-right: 50px">
    <span><b>Title:</b> ${requestScope.movie.title}</span> <br>
    <span><b>Release year:</b> ${requestScope.movie.year}</span> <br>
    <span><b>Country:</b> ${requestScope.movie.country}</span> <br><br>
    <span><b>Description:</b> ${requestScope.movie.description}</span> <br><br>
    <table style="width: 50%;margin-left:20px"><b>Movie Persons:</b><br>
        <tr style="text-align: left">
            <th>Name</th>
            <th>Role</th>
        </tr>

        <c:forEach var="moviePerson" items="${requestScope.moviePersons}">
            <tr>
                <td>${moviePerson.name}</td>
                <td>${moviePerson.role.title}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>

<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <h3 style="color: blueviolet;margin-left: 20px">Add movie person</h3>
    <form action="${pageContext.request.contextPath}/movies/${requestScope.movie.id}" method="post" style="margin-left: 20px">
        <label for="personId">Person:
            <select name="person" id="personId">
                <option value="" disabled selected>Select person</option>
                <c:forEach var="person" items="${requestScope.persons}">
                    <option value="${person.id}">${person.name}</option>
                </c:forEach>
            </select>
        </label>
        <label for="roleId">Role:
            <select name="role" id="roleId">
                <option value="" disabled selected>Select role</option>
                <c:forEach var="role" items="${requestScope.roles}">
                    <option value="${role.id}">${role.title}</option>
                </c:forEach>
            </select>
        </label>
        <input hidden name="movieId" value="${requestScope.movie.id}">
        <input hidden name="personFlag" value="true">
        <button type="submit">Add person</button>
        <br>
<%--        TODO How to do this?--%>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.message}</span><br>
                </c:forEach>
            </div>
        </c:if>
    </form>
</c:if>
<h3 style="color: blueviolet;margin-left: 20px">Add review</h3>
<form action="${pageContext.request.contextPath}/movies/${requestScope.movie.id}" method="post" style="margin-left: 20px">
    <label for="textId">Text:
        <input type="text" name="text" id="textId">
    </label>
    <label for="rateId">Rate (1-10):
        <input type="range" min="1" max="10" name="rate" id="rateId">
    </label>
    <input hidden name="movieId" value="${requestScope.movie.id}">
    <button type="submit">Add review</button>
    <br>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red;">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>

<h3 style="color: blueviolet;margin-left: 20px">All review</h3>
<div>
    <table style="width: 100%;margin-left:20px">
        <tr style="text-align: left">
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
