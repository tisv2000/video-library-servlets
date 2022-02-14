<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title 1</title>
</head>
<body>
<%@include file="header.jsp" %>
<h3 style="color: blueviolet">Filter</h3>
<form action="/movies" method="get">
    <label for="titleId">Title:
        <input type="text" name="title" id="titleId">
    </label>
    <label for="countryId" style="margin-left: 10px">Country:
        <input type="text" name="country" id="countryId">
    </label>
    <label for="yearId" style="margin-left: 10px">Release year:
        <input type="text" name="year" id="yearId">
    </label>
    <label for="genreId" style="margin-left: 10px">Genre:
        <select name="genre" id="genreId">
            <option value="" disabled selected>Select your option</option>
            <c:forEach var="genre" items="${requestScope.genres}">
                <option value="${genre.id}">${genre.title}</option>
            </c:forEach>
        </select>
    </label>
    <button type="submit" style="margin-left: 10px">Filter</button><br>
</form>
<h3 style="color: blueviolet">Add a new movie</h3>
<form action="/movies" method="post">
    <label for="addTitleId">Title:
        <input type="text" name="title" id="addTitleId">
    </label><br>
    <label for="addCountryId">Country:
        <input type="text" name="country" id="addCountryId">
    </label><br>
    <label for="addYearId">Release year:
        <input type="text" name="year" id="addYearId">
    </label><br>
    <label for="addGenreId">Genre:
        <select name="genre" id="addGenreId">
            <option value="" disabled selected>Select your option</option>
            <c:forEach var="genre" items="${requestScope.genres}">
                <option value="${genre.id}">${genre.title}</option>
            </c:forEach>
        </select>
    </label><br>
    <label for="addDescriptionId">Description:
        <input type="text" name="description" id="addDescriptionId">
    </label><br>
    <button type="submit">Add movie</button><br><br>
</form>
<h3 style="color: blueviolet">List of all movies</h3>
<table style="width: 60%">
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>Country</th>
        <th>Genre</th>
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
