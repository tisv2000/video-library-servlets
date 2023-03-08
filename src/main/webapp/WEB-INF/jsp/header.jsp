<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>Video Library</title>
</head>
<%--<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'ru_RU')}"/>--%>
<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="translations"/>
<div>
    <form action="/locale", method="post">
        <select name="lang" onchange="this.form.submit()">
            <option value=""></option>
            <option value="en">EN</option>
            <option value="ru">RU</option>
        </select>
    </form>
</div>
<div style="float:left;margin-left:20px">
    <a href="/movies">| Movies |</a>

    <a href="/persons">| Persons |</a>

    <a href="/myReviews">| My reviews |</a>

    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <a href="/reviews">| Reviews |</a>
    </c:if>
</div>

<div style="float:right">
    <b>${sessionScope.user.name}</b>

    <a href="/logout" style="margin-left: 20px;margin-right: 20px">Log out</a>
</div>
<br>

