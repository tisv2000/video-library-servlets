<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--url to servlet--%>
<form action="/registration" method="post">

    <label for="nameId">Name:
        <input type="text" name="name" id="nameId">
    </label><br>
    <label for="birthdayId">Birthday:
        <input type="date" name="birthday" id="birthdayId">
    </label><br>
    <label for="emailId">Email:
        <input type="text" name="email" id="emailId">
    </label><br>
    <label for="passwordId">Password:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <%--        ?role=user--%>
    <select name="role" id="roleId">Role
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select><br>
    <%--    ?gender=MALE--%>
    <c:forEach var="gender" items="${requestScope.genders}">
        <input type="radio" name="gender" value="${gender}"> ${fn:toLowerCase(gender)}
    </c:forEach><br>

    <button type="submit">Send</button>
</form>
</body>
</html>
