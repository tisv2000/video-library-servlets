<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Video Library</title>
</head>
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

