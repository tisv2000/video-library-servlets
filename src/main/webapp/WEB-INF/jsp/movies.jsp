<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<%@include file="header.jsp" %>
<h3 style="color:blueviolet;margin-left:20px;margin-right:20px"><fmt:message key="movie.filterTitle"/></h3>
<form action="${pageContext.request.contextPath}/movies" method="get">
    <label for="titleId" style="margin-left:20px"><fmt:message key="movie.title"/>:
        <input type="text" name="title" value="${param.name}" id="titleId">
    </label>
    <label for="countryId" style="margin-left: 10px"><fmt:message key="movie.country"/>:
        <input type="text" name="country" value="${param.country}" id="countryId">
    </label>
    <label for="yearId" style="margin-left: 10px"><fmt:message key="movie.year"/>:
        <input type="text" name="year" value="${param.year}" id="yearId">
    </label>
    <label for="genreId" style="margin-left: 10px"><fmt:message key="movie.genre"/>:
        <select name="genre" id="genreId">
            <option value="" disabled selected><fmt:message key="movie.selectGenreText"/></option>
            <c:forEach var="genre" items="${requestScope.genres}">
                <option value="${genre.id}">${genre.title}</option>
            </c:forEach>
        </select>
    </label>
    <button type="submit" style="margin-left: 10px;color: blueviolet"><fmt:message key="movie.filterButton"/></button>
    <br>
    <c:if test="${not empty requestScope.filterMovieErrors}">
        <div style="color: red;margin-left: 20px">
            <c:forEach var="error" items="${requestScope.filterMovieErrors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>

<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <h3 style="color: blueviolet;margin-left:20px"><fmt:message key="movie.addNewMovieTitle"/></h3>
    <form action="${pageContext.request.contextPath}/movies" method="post" enctype="multipart/form-data" style="margin-left: 20px">
        <label for="addTitleId"><fmt:message key="movie.newMovieTitle"/>:
            <input type="text" name="title" value="${param.title}" id="addTitleId">
        </label><br>
        <label for="addCountryId"><fmt:message key="movie.newMovieCountry"/>:
            <input type="text" name="country" value="${param.country}" id="addCountryId">
        </label><br>
        <label for="addYearId"><fmt:message key="movie.newMovieYear"/>:
            <input type="text" name="year" value="${param.year}" id="addYearId">
        </label><br>
        <label for="addGenreId"><fmt:message key="movie.newMovieGenre"/>:
            <select name="genre" id="addGenreId">
                <option value="" disabled selected><fmt:message key="movie.selectGenreText"/></option>
                <c:forEach var="genre" items="${requestScope.genres}">
                    <option value="${genre.id}">${genre.title}</option>
                </c:forEach>
            </select>
        </label><br>
        <label for="addDescriptionId"><fmt:message key="movie.newMovieDescription"/>:
            <input type="text" name="description" value="${param.description}" id="addDescriptionId">
        </label><br>
        <label for="imageId"><fmt:message key="movie.newMovieImage"/>:
            <input type="file" name="image" value="${param.image}" id="imageId">
        </label><br>
        <button type="submit" style="color: blueviolet"><fmt:message key="movie.newMovieAddButton"/></button>
        <br>
        <c:if test="${not empty requestScope.addMovieErrors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.addMovieErrors}">
                    <span>${error.message}</span><br>
                </c:forEach>
            </div>
        </c:if>
    </form>
</c:if>
<h3 style="color: blueviolet;float:left;margin-left:20px;margin-right:20px"><fmt:message key="movie.moviesListTitle"/></h3>
<table style="width: 100%;margin-left:20px">
    <tr style="text-align: left">
        <th><fmt:message key="movie.title"/></th>
        <th><fmt:message key="movie.year"/></th>
        <th><fmt:message key="movie.country"/></th>
        <th><fmt:message key="movie.genre"/></th>
    </tr>

    <c:forEach var="movie" items="${requestScope.movies}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/movies/${movie.id}">${movie.title}</a>
            </td>
            <td>${movie.year}</td>
            <td>${movie.country}</td>
            <td>${fn:toLowerCase(movie.genre.title)}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
