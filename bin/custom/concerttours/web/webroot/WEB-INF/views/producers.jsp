<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Producers</title>
</head>
<body>
<h1>Producers</h1>
<c:forEach var="producer" items="${producers}">
    <li>
        <a href="../concerttours/producers/${producer.name}">${producer.name}</a>
    </li>
</c:forEach>
</body>
</html>
