<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h3 style="color: darkblue;margin-left: 20px">Login</h3>
<form action="/login" method="post" style="margin-left: 20px">
    <label for="emailId">Email:
        <input type="text" name="email" value="${param.email}" id="emailId">
    </label><br>
    <label for="passwordId">Password:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <button type="submit" style="color: darkblue">Login</button>
    <br>
    <a href="/registration">
        <button type="button" style="color: darkcyan">Register</button>
    </a>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
