<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@include file="header.jsp" %>
<h2 style="margin-left: 20px;color: blueviolet"><fmt:message key="movieDetails.pageTitle"/></h2>
<div>
    <img src="/images/movies/${requestScope.movie.image}.jpeg" width="auto" height="50%"
         style="float:left;margin-left: 20px;margin-right: 10px"/>
    <div style="align-content:center;margin-right: 50px">
        <span><b><fmt:message key="movieDetails.title"/>:</b> ${requestScope.movie.title}</span> <br>
        <span><b><fmt:message key="movieDetails.year"/>:</b> ${requestScope.movie.year}</span> <br>
        <span><b><fmt:message key="movieDetails.country"/>:</b> ${requestScope.movie.country}</span> <br><br>
        <span><b><fmt:message key="movieDetails.description"/>:</b> ${requestScope.movie.description}</span> <br><br>
    </div>
</div><br>
<table style="width: 50%;margin-left:20px"><b><fmt:message key="movieDetails.moviePersonsTitle"/>:</b><br>
    <tr style="text-align: left">
        <th><fmt:message key="movieDetails.moviePerson.name"/></th>
        <th><fmt:message key="movieDetails.moviePerson.role"/></th>
    </tr>

    <c:forEach var="moviePerson" items="${requestScope.moviePersons}">
        <tr>
            <td>${moviePerson.name}</td>
            <td>${moviePerson.role.title}</td>
        </tr>
    </c:forEach>
</table>
<br>

<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <h2 style="color: blueviolet;margin-left: 20px"><fmt:message key="movieDetails.addMoviePerson.title"/></h2>
    <form action="${pageContext.request.contextPath}/movies/${requestScope.movie.id}" method="post"
          style="margin-left: 20px">
        <label for="personId"><fmt:message key="movieDetails.addMoviePerson.person"/>:
            <select name="person" id="personId">
                <option value="" disabled selected><fmt:message key="movieDetails.addMoviePerson.selectPersonText"/></option>
                <c:forEach var="person" items="${requestScope.persons}">
                    <option value="${person.id}">${person.name}</option>
                </c:forEach>
            </select>
        </label>
        <label for="roleId"><fmt:message key="movieDetails.addMoviePerson.role"/>:
            <select name="role" id="roleId">
                <option value="" disabled selected><fmt:message key="movieDetails.addMoviePerson.selectRoleText"/></option>
                <c:forEach var="role" items="${requestScope.roles}">
                    <option value="${role.id}">${role.title}</option>
                </c:forEach>
            </select>
        </label>
        <input hidden name="movieId" value="${requestScope.movie.id}">
        <input hidden name="addMoviePersonMode" value="true">
        <button type="submit" style="color: blueviolet"><fmt:message key="movieDetails.addMoviePerson.addPersonButton"/></button>
        <br>
        <c:if test="${not empty requestScope.addMoviePersonErrors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.addMoviePersonErrors}">
                    <span>${error.message}</span><br>
                </c:forEach>
            </div>
        </c:if>
    </form>
</c:if>
<h2 style="color: blueviolet;margin-left: 20px"><fmt:message key="movieDetails.addReview.title"/></h2>
<form action="${pageContext.request.contextPath}/movies/${requestScope.movie.id}" method="post"
      style="margin-left: 20px">
    <label for="textId"><fmt:message key="movieDetails.addReview.text"/>:
        <input type="text" name="text" id="textId">
    </label>
    <label for="rateId"><fmt:message key="movieDetails.addReview.rate"/> (1-10):
        <input type="range" min="1" max="10" name="rate" id="rateId">
    </label>
    <input hidden name="movieId" value="${requestScope.movie.id}">
    <button type="submit" style="color:blueviolet"><fmt:message key="movieDetails.addReview.addReviewButton"/></button>
    <br>
    <c:if test="${not empty requestScope.addReviewErrors}">
        <div style="color: red;">
            <c:forEach var="error" items="${requestScope.addReviewErrors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>

<h2 style="color: blueviolet;margin-left: 20px"><fmt:message key="movieDetails.allReviews.title"/></h2>
<div>
    <table style="width: 100%;margin-left:20px">
        <tr style="text-align: left">
            <th><fmt:message key="movieDetails.allReviews.rate"/></th>
            <th><fmt:message key="movieDetails.allReviews.review"/></th>
            <th><fmt:message key="movieDetails.allReviews.user"/></th>
        </tr>

        <c:forEach var="review" items="${requestScope.reviews}">
            <tr>
                <td>${review.rate}</td>
                <td>${review.text}</td>
                <td>${review.user.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
