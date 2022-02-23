<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<%@include file="header.jsp" %>
<h3 style="color:blueviolet;margin-left:20px;margin-right:20px">Filter</h3>
<form action="${pageContext.request.contextPath}/movies" method="get">
    <label for="titleId" style="margin-left:20px">Title:
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
    <button type="submit" style="margin-left: 10px;color: blueviolet">Filter</button>
    <br>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red;margin-left: 20px">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>

<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <h3 style="color: blueviolet;margin-left:20px">Add a new movie</h3>
    <form action="${pageContext.request.contextPath}/movies" method="post" enctype="multipart/form-data" style="margin-left: 20px">
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
        <label for="imageId">Image:
            <input type="file" name="image" id="imageId">
        </label><br>
        <button type="submit" style="color: blueviolet">Add movie</button>
        <br>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.message}</span><br>
                </c:forEach>
            </div>
        </c:if>
    </form>
</c:if>
<h3 style="color: blueviolet;float:left;margin-left:20px;margin-right:20px">List of all movies</h3>
<table style="width: 100%;margin-left:20px">
    <tr style="text-align: left">
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
