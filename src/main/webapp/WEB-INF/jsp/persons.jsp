<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp" %>
<h2 style="color: blue;margin-left:20px;margin-right:20px"><fmt:message key="person.filter.title"/></h2>
<form action="/persons" method="get" style="margin-left: 20px">
    <label for="nameId"><fmt:message key="person.filter.name"/>:
        <input type="text" name="name" value="${param.name}" id="nameId">
    </label>
    <button type="submit" style="margin-left: 10px;color: blue"><fmt:message key="person.filter.filterButton"/></button>
    <br>
    <c:if test="${not empty requestScope.filterPersonErrors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.filterPersonErrors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>

<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <h2 style="color: blue;margin-left:20px"><fmt:message key="person.addPerson.title"/></h2>
    <form action="${pageContext.request.contextPath}/persons" method="post" style="margin-left: 20px" enctype="multipart/form-data">
        <label for="addNameId"><fmt:message key="person.addPerson.name"/>:
            <input type="text" name="name" value="${param.name}" id="addNameId">
        </label><br>
        <label for="addBirthdayId"><fmt:message key="person.addPerson.birthday"/>:
            <input type="date" name="birthday" value="${param.birthday}" id="addBirthdayId">
        </label><br>
        <label for="imageId"><fmt:message key="person.addPerson.picture"/>:
            <input type="file" name="image" value="${param.image}" id="imageId">
        </label><br>
        <button type="submit" style="color: blue"><fmt:message key="person.addPerson.addButton"/></button>
        <br>
        <c:if test="${not empty requestScope.addPersonErrors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.addPersonErrors}">
                    <span>${error.message}</span><br>
                </c:forEach>
            </div>
        </c:if>
        <br>
    </form>
</c:if>
<h2 style="color: blue;margin-left:20px"><fmt:message key="person.allPersons.title"/></h2>
<table style="width: 100%;margin-left:20px">
    <tr style="text-align: left">
        <th><fmt:message key="person.allPersons.name"/></th>
        <th><fmt:message key="person.allPersons.birthday"/></th>
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
