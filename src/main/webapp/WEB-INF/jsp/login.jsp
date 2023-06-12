<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp" %>
<h2 style="color:darkblue;margin-left:20px"><fmt:message key="login.title"/></h2>
<form action="/login" method="post" style="margin-left: 20px">
    <label for="emailId"><fmt:message key="login.email"/>:
        <input type="text" name="email" value="${param.email}" id="emailId">
    </label><br>
    <label for="passwordId"><fmt:message key="login.password"/>:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <button type="submit" style="color: darkblue"><fmt:message key="login.loginButton"/></button>
    <br>
    <a href="/registration">
        <button type="button" style="color: darkcyan"><fmt:message key="login.registerButton"/></button>
    </a>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span><fmt:message key="${error.errorPropertyName}"/></span><br>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
