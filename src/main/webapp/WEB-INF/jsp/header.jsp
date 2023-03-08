<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>Video Library</title>
</head>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'en_US'}"/>
<fmt:setBundle basename="translations"/>
<div>
    <form action="${pageContext.request.contextPath}/locale" , method="post">
        <button type="submit" name="lang" value="en_US">EN</button>
        <button type="submit" name="lang" value="ru_RU">RU</button>
<%--                <select name="lang" onchange="this.form.submit()">--%>
<%--                    <option value="en_US">EN</option>--%>
<%--                    <option value="ru_RU">RU</option>--%>
<%--                </select>--%>
    </form>
</div>
<div style="float:left;margin-left:20px">
    <c:if test="${sessionScope.user != null}">
        <a href="/movies">| <fmt:message key="header.movies"/> |</a>

        <a href="/persons">| <fmt:message key="header.persons"/> |</a>

        <a href="/myReviews">| <fmt:message key="header.myReviews"/> |</a>
    </c:if>

    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <a href="/reviews">| <fmt:message key="header.reviews"/> |</a>
    </c:if>
</div>

<div style="float:right">
    <b>${sessionScope.user.name}</b>

    <c:if test="${sessionScope.user != null}">
        <a href="/logout" style="margin-left: 20px;margin-right: 20px"><fmt:message key="header.logout"/></a>
    </c:if>
</div>
<br>

