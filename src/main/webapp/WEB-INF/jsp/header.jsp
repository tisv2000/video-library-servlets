<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float:left;margin-left:10px">
    <a href="/movies">|   Movies   |</a>

    <a href="/persons">|   Persons   |</a>

    <c:if test="${sessionScope.user.role == 'USER'}">
        <a href="/myReviews">|   My reviews   |</a>
    </c:if>

    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <a href="/reviews">|   Reviews   |</a>
    </c:if>
</div>

<div style="float:right;margin-right:20px">
    ${sessionScope.user.name}

    <a href="/logout">
        Log out
    </a>
</div>
<br>

