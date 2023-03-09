<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp" %>
<h2 style="color:darkcyan;margin-left:20px;margin-right:20px"><fmt:message key="registration.title"/></h2>
<form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data"
      style="margin-left: 20px">
    <label for="nameId"><fmt:message key="registration.name"/>:
        <input type="text" name="name" value="${param.name}" id="nameId">
    </label><br>
    <label for="birthdayId"><fmt:message key="registration.birthday"/>:
        <input type="date" name="birthday" value="${param.birthday}" id="birthdayId">
    </label><br>
    <label for="emailId"><fmt:message key="registration.email"/>:
        <input type="text" name="email" value="${param.email}" id="emailId">
    </label><br>
    <label for="passwordId"><fmt:message key="registration.password"/>:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <select name="role" id="roleId"><fmt:message key="registration.role"/>
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select><br>
    <c:forEach var="gender" items="${requestScope.genders}">
        <input type="radio" name="gender" value="${gender}"> ${fn:toLowerCase(gender)}
    </c:forEach><br>
    <label for="imageId"><fmt:message key="registration.image"/>:
        <input type="file" name="image" id="imageId">
    </label><br>

    <button type="submit" style="color: darkcyan"><fmt:message key="registration.registerButton"/></button>
    <br>
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
