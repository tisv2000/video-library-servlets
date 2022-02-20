<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h3 style="color: blue">Filter</h3>
<form action="/persons" method="get">
    <label for="nameId">Name:
        <input type="text" name="name" id="nameId">
    </label>
    <button type="submit" style="margin-left: 10px">Filter</button>
    <br>
</form>

<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <h3 style="color: blue">Add a new person</h3>
    <form action="/persons" method="post">
        <label for="addNameId">Name:
            <input type="text" name="name" id="addNameId">
        </label><br>
        <label for="addBirthdayId">Birthday:
            <input type="date" name="birthday" id="addBirthdayId">
        </label><br>
        <button type="submit">Add Person</button>
        <br>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.message}</span><br>
                </c:forEach>
            </div>
        </c:if>
        <br>
    </form>
</c:if>
<h3 style="color: blue">List of all persons</h3>
<table style="width: 60%">
    <tr>
        <th>Name</th>
        <th>Birthday</th>
    </tr>

    <c:forEach var="person" items="${requestScope.persons}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/persons/${person.id}">${person.name}</a>
            </td>
            <td>${person.birthday}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
