<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<table>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr style="color:
            <c:out value="${meal.isExcess() ? 'red' : 'green'}"/>">
            <td>${meal.getDateTime().toString().replace("T", " ")}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
        </tr>
    </c:forEach>
</table>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
</body>
</html>