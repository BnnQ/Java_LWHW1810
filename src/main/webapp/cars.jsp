<%--@elvariable id="minManufacturer" type="java.lang.String"--%>
<%--@elvariable id="maxManufacturer" type="java.lang.String"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Car Information</title>
    <%@include file="layout.jsp"%>
</head>
<body>
<div class="container">
    <h1>Cars Information</h1>

    <h2>All Cars</h2>
    <%--@elvariable id="allCars" type="me.bnnq.homework.models.Car[]"--%>
    <c:forEach var="car" items="${allCars}">
        <p>${car}</p>
    </c:forEach>

    <h2>All Manufacturers</h2>
    <%--@elvariable id="allManufacturers" type="java.lang.String[]"--%>
    <c:forEach var="manufacturer" items="${allManufacturers}">
        <p>${manufacturer}</p>
    </c:forEach>

    <h2>Manufacturer and Number of Cars</h2>
    <%--@elvariable id="manufacturerCarCount" type="java.util.Dictionary<java.lang.String, java.lang.Integer>"--%>
    <c:forEach var="entry" items="${manufacturerCarCount}">
        <p>${entry.key}: ${entry.value}</p>
    </c:forEach>

    <h2>Manufacturer with Maximum Number of Cars</h2>
    <p>${maxManufacturer}</p>

    <h2>Manufacturer with Minimum Number of Cars</h2>
    <p>${minManufacturer}</p>

    <form action="cars" method="get">
        <%--@elvariable id="carsByYear" type="me.bnnq.homework.models.Car[]"--%>
        <h2>Cars of Specified Year</h2>
        <div class="input-group">
            <label for="yearInput" class="form-label">Enter a year:</label>
            <input type="number" id="yearInput" name="year" class="form-control" />
        </div>
        <button type="submit" class="btn btn-success mt-2">Submit</button>
        <c:if test="${not empty carsByYear}">
            <c:forEach var="car" items="${carsByYear}">
                <p>${car}</p>
            </c:forEach>
        </c:if>

        <h2>Cars in Year Range</h2>
        <div class="input-group">
            <label for="yearLeftBoundInput" class="form-label">Enter a left bound of years:</label>
            <input type="number" id="yearLeftBoundInput" name="yearLeftBound" class="form-control" />
        </div>
        <div class="input-group mt-1">
            <label for="yearRightBoundInput" class="form-label">Enter a right bound of years:</label>
            <input type="number" id="yearRightBoundInput" name="yearRightBound" class="form-control" />
        </div>
        <button type="submit" class="btn btn-success mt-2">Submit</button>
        <%--@elvariable id="carsInYearRange" type="me.bnnq.homework.models.Car[]"--%>
        <c:if test="${not empty carsInYearRange}">
            <c:forEach var="car" items="${carsInYearRange}">
                <p>${car}</p>
            </c:forEach>
        </c:if>

        <h2>Cars of Specified Manufacturer</h2>
        <div class="input-group">
            <label for="manufacturerInput" class="form-label">Enter a manufacturer:</label>
            <input type="text" id="manufacturerInput" name="manufacturer" class="form-control" />
        </div>
        <button type="submit" class="btn btn-success mt-2">Submit</button>
        <%--@elvariable id="carsOfManufacturer" type="me.bnnq.homework.models.Car[]"--%>
        <c:forEach var="car" items="${carsOfManufacturer}">
            <p>${car}</p>
        </c:forEach>

        <h2>Cars with Specified Color</h2>
        <div class="input-group">
            <label for="colorInput" class="form-label">Enter a color:</label>
            <input type="text" id="colorInput" name="color" class="form-control" />
        </div>
        <button type="submit" class="btn btn-success mt-2">Submit</button>
        <%--@elvariable id="carsOfColor" type="me.bnnq.homework.models.Car[]"--%>
        <c:forEach var="car" items="${carsOfColor}">
            <p>${car}</p>
        </c:forEach>

        <h2>Cars with Specified Engine Volume</h2>
        <div class="input-group">
            <label for="engineVolumeInput" class="form-label">Enter an engine volume:</label>
            <input type="number" step="0.1" id="engineVolumeInput" name="engineVolume" class="form-control" />
        </div>
        <button type="submit" class="btn btn-success mt-2">Submit</button>
        <%--@elvariable id="carsOfEngineVolume" type="me.bnnq.homework.models.Car[]"--%>
        <c:forEach var="car" items="${carsOfEngineVolume}">
            <p>${car}</p>
        </c:forEach>

        <h2>Cars of Specified Type</h2>
        <div class="input-group">
            <label for="typeInput" class="form-label">Enter a type:</label>
            <select class="form-select" name="type" id="typeInput">
                <option value="SEDAN">Sedan</option>
                <option value="HATCHBACK">Hatchback</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success mt-2">Submit</button>
        <%--@elvariable id="carsOfType" type="me.bnnq.homework.models.Car[]"--%>
        <c:forEach var="car" items="${carsOfType}">
            <p>${car}</p>
        </c:forEach>
    </form>

</div>
</body>
</html>