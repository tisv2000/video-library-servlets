<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp" %>
<h3 style="color: blue;margin-left:20px;margin-right:20px">Filter</h3>
<form action="/persons" method="get">
    <label for="nameId" style="margin-left:20px">Name:
        <input type="text" name="name" id="nameId">
    </label>
    <button type="submit" style="margin-left: 10px;color: blue">Filter</button>
    <br>
</form>

<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <h3 style="color: blue;margin-left:20px">Add a new person</h3>
    <form action="/persons" method="post" style="margin-left: 20px">
        <label for="addNameId">Name:
            <input type="text" name="name" id="addNameId">
        </label><br>
        <label for="addBirthdayId">Birthday:
            <input type="date" name="birthday" id="addBirthdayId">
        </label><br>
        <button type="submit" style="color: blue">Add Person</button>
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
<h3 style="color: blue;margin-left:20px">List of all persons</h3>
<table style="width: 100%;margin-left:20px">
    <tr style="text-align: left">
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
