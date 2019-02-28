<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<p>Add/Update meal</p>
<c:set var="dateTimeWithoutT" value="${fn:replace(meal.dateTime, 'T', ' ')}"/>
<form method="post" action="meals" name="addMeal">
    MealId <input type="text" readonly="readonly" name="mealId"
                  value="<c:out value="${meal.id}" />"/> <br/>
    DateTime <input type="datetime-local" name="dateTime"
                    value="<c:out value="${meal.dateTime}" />"/> <br/>
    Description<input type="text" name="description"
                      value="<c:out value="${meal.description}" />"/> <br/>
    Calories<input type="number" name="calories"
                   value="<c:out value="${meal.calories}" />"/> <br/>

    <button type="submit">Add meal</button>
</form>
</body>
</html>